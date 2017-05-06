package server;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author nj1s4y3h5
 */
public class Server {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, AWTException{
        socket();
       
    }
    public static void enterString(Robot rbt, String instr){
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection strsel = new StringSelection(instr);
        clip.setContents(strsel,strsel);
        
        rbt.keyPress(KeyEvent.VK_CONTROL);
        rbt.keyPress(KeyEvent.VK_V);
        rbt.keyRelease(KeyEvent.VK_CONTROL);
        rbt.keyRelease(KeyEvent.VK_V);
        rbt.keyPress(KeyEvent.VK_ENTER);
        rbt.keyRelease(KeyEvent.VK_ENTER);
    }
public static void socket() throws IOException, AWTException{
    Robot rbt = new Robot();
    int x = 0;
    int y = 0;
    ServerSocket socket = new ServerSocket(4445);
    Socket client = socket.accept();
    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
    System.out.println("Connected");
    
    String instr = "";
    while(!instr.equals("exit")){
    instr = in.readLine();
    if(instr.equals("c")){
        String command = "cmd /c start chrome.exe";
        Process proc = Runtime.getRuntime().exec(command);
        instr = in.readLine();
        enterString(rbt, instr);
    }else if(instr.equals("restert")){
        String command = "shutdown -r";
        Process proc = Runtime.getRuntime().exec(command);
        }else if(instr.equals("shutdown")){
        String command = "shutdown -s -t 0";
        Process proc = Runtime.getRuntime().exec(command);
    }else if(instr.equals("lock")){
        rbt.keyPress(KeyEvent.VK_WINDOWS);
        rbt.keyPress(KeyEvent.VK_L);
        rbt.keyRelease(KeyEvent.VK_WINDOWS);
        rbt.keyRelease(KeyEvent.VK_L);
    }else if(instr.equals("exp")){
        String command = "cmd /c start explorer.exe";
        Process proc = Runtime.getRuntime().exec(command);
    }else if(instr.equals("mouse")){
        System.out.println("Moving Mouse Now");
        while(!instr.equals("stop")){
            instr = in.readLine();
            if(instr.equals("click")){
               rbt.mousePress(InputEvent.BUTTON1_MASK);
               rbt.delay(50);
               rbt.mouseRelease(InputEvent.BUTTON1_MASK);
            }else if(instr.equals("dclick")){
               rbt.mousePress(InputEvent.BUTTON1_MASK);
               rbt.delay(50);
               rbt.mouseRelease(InputEvent.BUTTON1_MASK);
               rbt.delay(50);
               rbt.mousePress(InputEvent.BUTTON1_MASK);
               rbt.delay(50);
               rbt.mouseRelease(InputEvent.BUTTON1_MASK);
            }else if(instr.equals("str")){
                instr = in.readLine();
                enterString(rbt, instr);
            }else if(instr.equals("del")){
                enterString(rbt, "");
            }else{
            String co[] = instr.split(",");
            String co1 = co[0];
            String co2 = co[1];
            x = Integer.parseInt(co1);
            y = Integer.parseInt(co2);
            rbt.mouseMove(x, y);
            }
        }
    }
 }
}
}


