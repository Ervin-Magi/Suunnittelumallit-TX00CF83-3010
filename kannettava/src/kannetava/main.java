package kannettava;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

import lejos.robotics.geometry.Line;
import lejos.robotics.geometry.Rectangle;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;

public class main {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		
		// Otetaan yhteys robottiin
		Socket s = new Socket("10.0.1.1", 1111);
		DataOutputStream out = new DataOutputStream(s.getOutputStream());
		DataInputStream in = new DataInputStream(s.getInputStream());
		
		
		Scanner lukija = new Scanner(System.in);
		
		// Aloitetaan kÃ¤yttÃ¶liittymÃ¤
		while(true) {
			System.out.println();
			System.out.println("1: Rakentaa kartan");
			System.out.println("2: LÃ¤hettÃ¤Ã¤ waypointit");
			System.out.println("0: Lopettaa ohjelman");
			System.out.println("Anna numero: ");
			int numero = lukija.nextInt();
			
			switch (numero) {
			case 1:
				// LÃ¤hetetÃ¤Ã¤n kartta robotille
				Line[] janat = new Line[9];
				Rectangle suorakulmio = new Rectangle(0,0,100,100);
				
				// Suorakulmio
				janat[0] = new Line(0,0,100,0);
				janat[1] = new Line(100,0,100,100);
				janat[2] = new Line(100,100,0,100);
				janat[3] = new Line(0,100,0,0);
				
				// Este
				janat[4] = new Line(43,73 , 43,100);
				janat[5] = new Line(50,55,100,55);
				janat[6] = new Line(50,55,50,52);
				janat[7] = new Line(50,52,100,52);
				janat[8] = new Line(45,0,45,29);
				
				LineMap kartta = new LineMap(janat, suorakulmio);
				
				kartta.dumpObject(out);
				System.out.println("Kartta lÃ¤hetetty");
				System.out.println();
				break;
				
			case 2:
				// LÃ¤hetettÃ¤vÃ¤t waypointit
				int x = 15, y = 15, d = 0;
				Waypoint pisteA = new Waypoint(18, 43);
				Waypoint pisteB = new Waypoint(71, 80);
				Waypoint pisteC = new Waypoint(82, 28);
				Pose alkusijainti = new Pose(x, y, d);
				
				pisteA.dumpObject(out);
				pisteB.dumpObject(out);
				pisteC.dumpObject(out);
				alkusijainti.dumpObject(out);
				System.out.println(in.readUTF());
				System.out.println(in.readUTF());
				System.out.println(in.readUTF());
				break;
				
			case 0:
				// Lopetetaan suoritus
				System.exit(0);
				break;

			}
		}
		
		
		

		
	}

}