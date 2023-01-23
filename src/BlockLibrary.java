import java.util.Random;
import java.util.Arrays;

public class BlockLibrary {
    
    Block savedBlocks[];

    public BlockLibrary(Block blocks[], int newWidth) {

        int arrayLength = blocks.length;
        savedBlocks = new Block[arrayLength];

        for(int i = 0; i < arrayLength; i++){
            savedBlocks[i] = new Block(blocks[i]);
        }
        //Sort blocks by their height
        int h;

        for(h = 1; h <= savedBlocks.length / 9; h = h * 3 + 1);

        while (h >= 1) {
            for (int i = h; i < savedBlocks.length; i++)
                for(int j = i - h; j >= 0 && savedBlocks[j].GetUpY() > savedBlocks[j+h].GetUpY(); j-=h){
                    Block temp = new Block(savedBlocks[j]);
                
                    savedBlocks[j].SetData(savedBlocks[j + h]);
                    savedBlocks[j+h].SetData(temp);
                }
            
            h = (h - 1) / 3;
        }

        ChangeWidth(newWidth);
    }

    public Block[] GetResult() {
        return savedBlocks;
    }

    public void ChangeWidth(int newWidth)
    {

        // Find to divide

        int maxX = 0;
        int arrayLength = savedBlocks.length;

        for (int i = 0; i < arrayLength; i++) {
            if (savedBlocks[i].GetRightX() > maxX)
                maxX = savedBlocks[i].GetRightX();
        }

        double divider = (float)newWidth / (float)maxX;

        System.out.println(divider);

        for (int i = 0; i < arrayLength; i++) {

            int xLeftToPlace = savedBlocks[i].GetLeftX();
            int xRightToPlace = savedBlocks[i].GetRightX();
            int yUpToPlace = savedBlocks[i].GetUpY();
            int yDownToPlace = savedBlocks[i].GetDownY();

            if (xLeftToPlace > xRightToPlace)
                {
                    int temp = xLeftToPlace;
                    xLeftToPlace = xRightToPlace;
                    xRightToPlace = temp;
                }

            if (yUpToPlace > yDownToPlace) {
                int temp = yDownToPlace;
                yDownToPlace = yUpToPlace;
                yUpToPlace = temp;
            }

            savedBlocks[i].SetData((int)(xLeftToPlace*divider), (int)(xRightToPlace*divider), 
                    (int)(yUpToPlace * divider), (int)(yDownToPlace * divider), savedBlocks[i].GetText());

            int yDif = savedBlocks[i].GetDownY() - savedBlocks[i].GetUpY();
            int xDif = savedBlocks[i].GetRightX() - savedBlocks[i].GetLeftX();

            int addY = 0;
            while (xDif * (yDif+addY) < 50){
                addY++;
            }

            savedBlocks[i].SetData((int) (savedBlocks[i].GetLeftX()),
                    (int) (savedBlocks[i].GetRightX()),
                    (int) (savedBlocks[i].GetUpY()), 
                    (int) (savedBlocks[i].GetDownY()+addY),
                    savedBlocks[i].GetText());
        }
    }

    public static void main(String[] args)  {

        int n = 5;
        Test1 AnswerTest[], testCopy[];
        AnswerTest = new Test1[n];
        testCopy = new Test1[n];
        int Min = 1, Max = 1;

        for(int i = 0; i < AnswerTest.length; i++){
            AnswerTest[i] = new Test1(Min);
            Min = AnswerTest[i].GetUpY();
            if (AnswerTest[i].GetRightX() > Max)
                Max = AnswerTest[i].GetRightX();
            //testCopy[i] = AnswerTest[i];
        }
        //testCopy = Arrays.copyOf(AnswerTest, AnswerTest.length);
        //testCopy = Array.from(AnswerTest);
        testCopy = AnswerTest.clone();

        //вид блоков который должен быть
        for(int i = 0; i < AnswerTest.length; i++){
            System.out.println("("+AnswerTest[i].GetLeftX()+", "+ AnswerTest[i].GetRightX()+", "+ AnswerTest[i]
                    .GetUpY()+", "+ AnswerTest[i].GetDownY()+", |"+ AnswerTest[i].GetText()+"|)");
        }
        System.out.println(" ");

        //раскидываем массив блоков случайно
        Random rnd = new Random();
        for (int i = 1; i < testCopy.length; i++) {
            int j = rnd.nextInt(i);
            Test1 temp = testCopy[i];
            testCopy[i] = testCopy[j];
            testCopy[j] = temp;
        }

        //изменение блоков под вид который будет видить user в начале
        double Coef = 0.6667;
        for(int i = 0; i < testCopy.length; i++){
            testCopy[i].SetTestBlocks(Coef);
        }

        //изначальный вид блоков
        for(int i = 0; i < testCopy.length; i++){
            System.out.println("("+testCopy[i].GetLeftX()+", "+ testCopy[i].GetRightX()+", "+ testCopy[i]
                    .GetUpY()+", "+ testCopy[i].GetDownY()+", |"+ testCopy[i].GetText()+"|)");
        }

        Block blocks[];
        blocks = new Block[testCopy.length];
        for(int i = 0; i < testCopy.length; i++){
            blocks[i] = new Block(testCopy[i].GetLeftX(), testCopy[i].GetRightX(), testCopy[i].GetUpY(),
                    testCopy[i].GetDownY(), testCopy[i].GetText());
        }

        Block testArray[];
        testArray = new Block[testCopy.length];
        for(int i = 0; i < testCopy.length; i++){
            testArray[i] = new Block(blocks[i]);
        }

        BlockLibrary bl = new BlockLibrary(testArray, Max);
        System.out.println(Max);
        //bl.ChangeWidth(Max);

        Block resultArray[];
        resultArray = bl.GetResult();

        for(int i = 0; i < resultArray.length; i++)
        {
            System.out.println("("+resultArray[i].GetLeftX()+", "+ resultArray[i].GetRightX()+", "+ resultArray[i]
                    .GetUpY()+", "+ resultArray[i].GetDownY()+", |"+ resultArray[i].GetText()+"|)");
        } //вывод проги (ее вариант)
    }


}
