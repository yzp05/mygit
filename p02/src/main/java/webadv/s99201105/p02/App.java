package webadv.s99201105.p02;

import java.io.*;
import java.util.Scanner;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		/*
		 * if (args.length < 1) { System.err.println("Please provide an input!");
		 * System.exit(0); }
		 */    
    	Scanner scan = new Scanner(System.in);
    	System.out.println("请输入账号：");
        String account = scan.next();
        System.out.println("请输入密码：");
        String password = scan.next();
        String ps = sha256hex(password);
        //System.out.println(ps);
        int tag = check(account,ps);
        if(tag==1) {
        	System.out.println("登录成功！");
        }else {
        	System.out.println("登录失败！");
        }
    }
    public static String sha256hex(String input) {
        return DigestUtils.sha256Hex(input);
    }
    
    public static int check(String account ,String ps) {
    	File file = new File("account_password.txt");
    	if(file.exists()) {
    		try {
    			FileInputStream fis =  new FileInputStream(file);
    			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
    			String line ;
				try {
					while((line=br.readLine())!=null) {
						String[] st = line.split(" ");
						if(st[0].equals(account)&&st[1].contains(ps)) {
							return 1;
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
    	}
    	return 0;
    }
}
