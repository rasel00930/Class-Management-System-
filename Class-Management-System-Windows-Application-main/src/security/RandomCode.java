package security;


public class RandomCode {
    
    public static int getVerificationCode(){
        int code = (int)(899999 * Math.random() + 100000);
        return code;
    }
    
    public static String getRandomCode(){
        int codeLength = 6;
        String allCharacters = "abcdefghijklm0123456789nopqrstuvxyz";
        StringBuilder sb = new StringBuilder(codeLength);
        
        for(int i = 0; i < codeLength; i++){
            int index = (int) (allCharacters.length() * Math.random());
            sb.append(allCharacters.charAt(index));
        }
        return sb.toString();
    }
}
