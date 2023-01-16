import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.geometry.Line;
import lejos.robotics.geometry.Rectangle;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.DestinationUnreachableException;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.AstarSearchAlgorithm;
import lejos.robotics.pathfinding.FourWayGridMesh;
import lejos.robotics.pathfinding.NodePathFinder;
import lejos.robotics.pathfinding.Path;
import lejos.robotics.pathfinding.PathFinder;
import lejos.robotics.pathfinding.ShortestPathFinder;

public class MovePilotTest {
	public static void main(String[] args) throws DestinationUnreachableException {
		final int MAX_X = 100, MAX_Y = 100;
		int wpX, wpY;
		
		// Moottorien alustus
		
		RegulatedMotor left = new EV3LargeRegulatedMotor(MotorPort.B);
		RegulatedMotor right = new EV3LargeRegulatedMotor(MotorPort.A);
		
		Wheel wheel1 = WheeledChassis.modelWheel(left, 4.32).offset(6.6);
		Wheel wheel2 = WheeledChassis.modelWheel(right, 4.32).offset(-6.6);
		
		Chassis chassis = new WheeledChassis(new Wheel[] {wheel1, wheel2}, WheeledChassis.TYPE_DIFFERENTIAL);
		MovePilot pilot = new MovePilot(chassis);
		
		PoseProvider pp = chassis.getPoseProvider();
		
		
		
		Navigator navi = new Navigator(pilot, pp);
		
		// TAULUKOT
		
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
		
		ShortestPathFinder polunetsijÃ¤ = new ShortestPathFinder(kartta);
		polunetsijÃ¤.lengthenLines(12);
		
	
		int x = 15, y = 15, d = 0;
		
		Waypoint pisteA = new Waypoint(18, 43);
		Waypoint pisteB = new Waypoint(71, 80);
		Waypoint pisteC = new Waypoint(82, 28);
		//Waypoint lopetus = new Waypoint(15, 15);
		
		Pose alkusijainti = new Pose(x, y, d);
		Waypoint alku = new Waypoint(x, y);
		
		
		Pose sijainti = navi.getPoseProvider().getPose();
		
		
		Path eka = polunetsijÃ¤.findRoute(alkusijainti, pisteA);
		
		navi.setPath(eka);
		navi.followPath();
		
		navi.waitForStop();
		sijainti = navi.getPoseProvider().getPose();
		System.out.println("1. X :" + sijainti.getX() + "	Y:" + sijainti.getY());
		
		Path AB = polunetsijÃ¤.findRoute(sijainti, pisteB);
		
		navi.setPath(AB);
		navi.followPath();
		navi.waitForStop();
		sijainti = navi.getPoseProvider().getPose();
		System.out.println("2. X :" + sijainti.getX() + "	Y:" + sijainti.getY());
		
		Path BC = polunetsijÃ¤.findRoute(sijainti, pisteC);
		
		navi.setPath(BC);
		navi.followPath();
		navi.waitForStop();
		sijainti = navi.getPoseProvider().getPose();
		System.out.println("3. X :" + sijainti.getX() + "	Y:" + sijainti.getY());
		
		Path Ceka = polunetsijÃ¤.findRoute(sijainti, alku);
		
		navi.setPath(Ceka);
		navi.followPath();
		navi.waitForStop();

		pilot.stop();

	}
}