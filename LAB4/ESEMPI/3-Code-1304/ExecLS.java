import java.io.*;

public class ExecLS{
    public static void main(String[] args){
        try{
            String[] cmd={"ls","-la"};
            Process pr=Runtime.getRuntime().exec(cmd);
            BufferedReader bf=new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line=bf.readLine();
            while(line!=null){
                System.out.println(line);
                line=bf.readLine();
            }
            bf.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
