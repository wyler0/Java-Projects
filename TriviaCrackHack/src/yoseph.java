//https://api.preguntados.com/api/users/98652206/games/4459430805?_=4459430805
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class yoseph
{
	JDialog frame = new JDialog(new JFrame(), "Made By Wyler Zahm");
	JLabel category;
	JLabel answer;
	JTextField fieldUserID = new JTextField();
	JTextField fieldGameID = new JTextField();
	JTextField fieldCookieData = new JTextField();
	String UserID = "";//The first series of number
	String GameID = "";//The second series of numbers
	String CookieData = "";//In cookie attribute
	String time="44511430805";
	JButton go;
	public yoseph(){
		frame.setLayout(new FlowLayout());
		frame.setSize(200, 200);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setBackground(new Color(0, 255, 50));
		go = new JButton();
		go.setText("Go");
		go.setSize(200,20);
		category = new JLabel("                Fill form                ");
		category.setSize(300, 30);
		category.setOpaque(true);
		category.setBackground(new Color(255,0,255));
		answer = new JLabel("                then hit go                ");
		answer.setSize(300, 30);
		answer.setOpaque(true);
		answer.setBackground(new Color(255,0,255));
		fieldUserID.setText("UsedID Here");
		fieldGameID.setText("GameID Here");
		fieldCookieData.setText("CookieData Here");
		frame.setTitle("TriviaCrack Hacker");
		frame.setResizable(false);
		frame.getContentPane().add(fieldUserID);
		frame.getContentPane().add(fieldGameID);
		frame.getContentPane().add(fieldCookieData);
		frame.getContentPane().add(go);
		frame.getContentPane().add(category);
		frame.getContentPane().add(answer);
		frame.getContentPane().add(new JLabel("       Made By Wyler Zahm       "));
		frame.setSize(200, 300);
		go.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UserID = fieldUserID.getText();
					GameID = fieldGameID.getText();
					CookieData = fieldCookieData.getText();
					String[] answers = (GetUserData(UserID,GameID, time, CookieData));
					category.setText(answers[0]);
					answer.setText(answers[1]);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}          
		});
	}
	public String[] GetUserData (String UserID,String GameID, String TimestampInMilliseconds, String cookiedata) throws Exception 
	{
		String url = "https://api.preguntados.com/api/users/" +UserID + "/games/"+GameID+"?_=" + TimestampInMilliseconds + "";
		System.out.println(url);
		try{
			URL obj = new URL(url);
			URLConnection conn = obj.openConnection();
			conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0");
			conn.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			conn.addRequestProperty("Cookie", cookiedata);
			BufferedReader br = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String inputLine = br.readLine();
			String[] inputLiner = inputLine.split("spins");
			br.close();
			System.out.println(inputLine);
			String finaltext = "";
			inputLine = inputLiner[2];
			inputLiner = inputLine.split("category\":\"");

			finaltext+= inputLiner[1].split("\",\"text")[0];
			System.out.println("\nCategory is: " + inputLiner[1].split("\",\"text")[0]);
			//System.out.println(inputLiner[1]);
			inputLiner = inputLiner[1].split("correct_answer\":");
			System.out.println("Answer is: " + inputLiner[1].split(",\"media_type")[0]);
			return new String[]{("Category: " + finaltext)," \nAnswer: " + (Integer.parseInt(inputLiner[1].split(",\"media_type")[0])+1)};
		}
		catch(Exception e){
			System.out.println("Error, make sure you enter the corrent information and that the game is started. ");
			return new String[]{"Invalid Data", "Re-enter Data"};
		}

	}
}