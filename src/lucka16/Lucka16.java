package lucka16;

public class Lucka16 {

    private String data;

    public Lucka16() {
        data = "";
    }

    public static void main(String[] args) {

        Lucka16 lucka16 = new Lucka16();

        /*
        // test
        lucka16.setData("111100001010");
        lucka16.dragonCurveMe();
        lucka16.print();
        
        // test 2
        lucka16 = new Lucka16();
        lucka16.setData("10000");
        lucka16.dargonCurveToSize(20);
        lucka16.print();
        */
        
        // solve I
        lucka16 = new Lucka16();
        lucka16.setData("11011110011011101");
        lucka16.dargonCurveToSize(272);
        lucka16.print();

     
        // solve II
        lucka16 = new Lucka16();
        lucka16.setData("11011110011011101");
        lucka16.dargonCurveToSize(35651584);
        System.out.println("--DragonCurveComplete done---");
        System.out.println(lucka16.checksum());
        

        

    }

    private void setData(String string) {
        data = string;
    }

    private void print() {
        System.out.println(data);
        System.out.println("checksum " + checksum());
                
    }

    private void dragonCurveMe() {
        String b = new StringBuilder(data).reverse().toString();
        b = b.replace('1', ':');
        b = b.replace('0', '1');
        b = b.replace(':', '0');
        data = data + "0" + b;
    }

    private void dargonCurveToSize(int size) {
        while (data.length()<size) {
            dragonCurveMe();
        }
        data = data.substring(0, size);
    }

    private String checksum() {
        return checkSum(data);
        
    }

    private String checkSum(String s) {
        return checkSum(new StringBuilder(s));
    }
    
    
    private String checkSum(StringBuilder input) {
      
        if (input.length()%2>0) return input.toString();
        
        StringBuilder sb = new StringBuilder();
        for (int pos=0; pos<input.length()-1; pos+=2) {
            sb.append((input.charAt(pos)==input.charAt(pos+1))?'1':'0');
        }
        
        return checkSum(sb);

    }
    
    
}
