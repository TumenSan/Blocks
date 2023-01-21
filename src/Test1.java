public class Test1 {
    private int leftX = 0;
    private int rightX = 0;
    private int upY = 0;
    private int downY = 0;

    private String text = "";

    public Test1() {

        leftX = (int)(Math.random() * (1000 - 0)) + 0;
        rightX = (int)(Math.random() * (1000 - 0)) + 0;
        upY = (int)(Math.random() * (1000 - 0)) + 0;
        downY = (int)(Math.random() * (1000 - 0)) + 0;

        text = "newText";

    }

    public Test1(int MinUp) {

        leftX = (int)(Math.random() * (1000 - 0)) + 0;
        rightX = (int)(Math.random() * (1000 - 0)) + 0;
        if(MinUp != 1000)
            upY = (int)(Math.random() * (1000 - MinUp)) + MinUp + 1;
        else upY = 1000;
        downY = (int)(Math.random() * (1000 - 0)) + 0;

        text = "newText";

    }

    public Test1(Block newBlock)
    {
        leftX = newBlock.GetLeftX();
        rightX = newBlock.GetRightX();
        upY = newBlock.GetUpY();
        downY = newBlock.GetDownY();

        text = newBlock.GetText();
    }

    public void SetTestBlocks(double Coef) {
        leftX = (int)(leftX / Coef);
        rightX = (int)(rightX / Coef);
        upY = (int)(upY / Coef);
        downY = (int)(downY / Coef);

        text = "12345abc";
    }

    public void SetData(Block newBlock)
    {
        leftX = newBlock.GetLeftX();
        rightX = newBlock.GetRightX();
        upY = newBlock.GetUpY();
        downY = newBlock.GetDownY();

        text = newBlock.GetText();
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
