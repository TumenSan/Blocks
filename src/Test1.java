import java.util.Random;

public class Test1 {
    private int leftX = 0;
    private int rightX = 0;
    private int upY = 0;
    private int downY = 0;

    private String text = "";

    public Test1(int MinUp) {

        leftX = (int)(Math.random() * (2000 - 0)) + 0;
        rightX = (int)(Math.random() * (2000 - leftX)) + leftX;
        if(MinUp != 2000)
            upY = (int)(Math.random() * (2000 - MinUp)) + MinUp + 1;
        else upY = 2000;
        downY = (int)(Math.random() * (2000 - upY)) + upY;

        Random random = new Random();

        text = "";
        for (int i = 0; i < 10; i++) {
            char c = (char)(random.nextInt(26) + 'a');
            text = text + c;
        }

    }

    public void SetTestBlocks(double Coef) {
        leftX = (int)(leftX / Coef);
        rightX = (int)(rightX / Coef);
        upY = (int)(upY / Coef);
        downY = (int)(downY / Coef);
    }

    public void CheckAnswer(Block newBlock)
    {
        int flag = 0, a = 0;
        a = Math.abs((leftX - (int)newBlock.GetLeftX()));
        System.out.println(leftX);
        System.out.println((int)newBlock.GetLeftX());
        System.out.println(a);
        System.out.println(1111111);
        if(Math.abs((leftX - (int)newBlock.GetLeftX())) > 1) {
            flag = flag + 1;
            System.out.println(flag);
        }
        if(Math.abs((rightX - (int)newBlock.GetLeftX())) > 1) {
            flag = flag + 1;
            System.out.println(flag);
        }
        if(Math.abs((upY - (int)newBlock.GetLeftX())) > 1) {
            flag = flag + 1;
            System.out.println(flag);
        }
        if(Math.abs((downY - (int)newBlock.GetLeftX())) > 1) {
            flag = flag + 1;
            System.out.println(flag);
        }
        if(text == newBlock.GetText()) {
            flag = flag + 1;
            System.out.println(flag);
        }
        if (flag == 0)
            System.out.println("Right");
        else System.out.println("Wrong");
        System.out.println();
    }

    public void SetData(int newLeftX, int newRightX, int newUpY, int newDownY, String newText) {

        leftX = newLeftX;
        rightX = newRightX;
        upY = newUpY;
        downY = newDownY;

        text = newText;

    }

    public int GetLeftX(){
        return leftX;
    }

    public int GetRightX() {
        return rightX;
    }

    public int GetUpY() {
        return upY;
    }

    public int GetDownY() {
        return downY;
    }

    public String GetText() {
        return text;
    }
}
