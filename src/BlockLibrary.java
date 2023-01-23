import java.util.Random;

public class BlockLibrary {
    
    Block savedBlocks[];

    public BlockLibrary(Block blocks[]) {

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

            if (yDownToPlace > yUpToPlace) {
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
        Test1 test1[], testCopy[];
        test1 = new Test1[n];
        testCopy = new Test1[n];
        int Min = 1, Width = 150, Max = 1;
        double Coef = 0.6667;
        for(int i = 0; i < n; i++){
            test1[i] = new Test1(Min);
            Min = test1[i].GetUpY();
            if (test1[i].GetRightX() > Max)
                Max = test1[i].GetRightX();
            testCopy[i] = test1[i];
        }

        for(int i = 0; i < n; i++){
            System.out.println("("+test1[i].GetLeftX()+", "+ test1[i].GetRightX()+", "+ test1[i]
                    .GetUpY()+", "+ test1[i].GetDownY()+", |"+ test1[i].GetText()+"|)");
        }
        System.out.println(" "); //вид блоков который должен быть

        Random rnd = new Random();
        for (int i = 1; i < testCopy.length; i++) {
            int j = rnd.nextInt(i);
            Test1 temp = testCopy[i];
            testCopy[i] = testCopy[j];
            testCopy[j] = temp;
        }

        for(int i = 0; i < n; i++){
            testCopy[i].SetTestBlocks(Coef);
        }
/*
        for(int i = 0; i < n; i++){
            System.out.println("("+test1[i].GetLeftX()+", "+ test1[i].GetRightX()+", "+ test1[i]
                    .GetUpY()+", "+ test1[i].GetDownY()+", |"+ test1[i].GetText()+"|)");
        }
 */

        for(int i = 0; i < n; i++){
            System.out.println("("+testCopy[i].GetLeftX()+", "+ testCopy[i].GetRightX()+", "+ testCopy[i]
                    .GetUpY()+", "+ testCopy[i].GetDownY()+", |"+ testCopy[i].GetText()+"|)");
        } //изначальный вид блоков

        Block blocks[];
        blocks = new Block[n];
        for(int i = 0; i < n; i++){
            blocks[i] = new Block(testCopy[i].GetLeftX(), testCopy[i].GetRightX(), testCopy[i].GetUpY(),
                    testCopy[i].GetDownY(), testCopy[i].GetText());
        }

        Block testArray[];
        testArray = new Block[5];
        testArray[0] = new Block(blocks[0]);
        testArray[1] = new Block(blocks[1]);
        testArray[2] = new Block(blocks[2]);
        testArray[3] = new Block(blocks[3]);
        testArray[4] = new Block(blocks[4]);

        BlockLibrary bl = new BlockLibrary(testArray);
        System.out.println(Max);
        bl.ChangeWidth(Max);

        Block resultArray[];
        resultArray = bl.GetResult();

        for(int i = 0; i < resultArray.length; i++)
        {
            System.out.println("("+resultArray[i].GetLeftX()+", "+ resultArray[i].GetRightX()+", "+ resultArray[i]
                    .GetUpY()+", "+ resultArray[i].GetDownY()+", |"+ resultArray[i].GetText()+"|)");
        } //вывод проги (ее вариант)
    }


}
