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

public class MovePilotTest2 {
	public static void main(String[] args) throws DestinationUnreachableException {
		final int MAX_X = 100, MAX_Y = 100;
		int wpX, wpY;
		
		// Moottorien alustus
		
		RegulatedMotor left = new EV3LargeRegulatedMotor(MotorPort.B);
		RegulatedMotor right = new EV3LargeRegulatedMotor(MotorPort.A);
		
		Wheel wheel1 = WheeledChassis.modelWheel(left, 4.32).offset(6.7);
		Wheel wheel2 = WheeledChassis.modelWheel(right, 4.32).offset(-6.7);
		
		Chassis chassis = new WheeledChassis(new Wheel[] {wheel2, wheel1}, WheeledChassis.TYPE_DIFFERENTIAL);
		MovePilot pilot = new MovePilot(chassis);
		
		PoseProvider pp = chassis.getPoseProvider();
		Navigator navi = new Navigator(pilot, pp);
		
		// TAULUKOT
		
		Line[] janat = new Line[8];
		Rectangle suorakulmio = new Rectangle(0,0,150,150);
		
		// Suorakulmio + oviaukko
		/*janat[0] = new Line(0,0,30,0);
		janat[1] = new Line(70,0,100,0);*/
		
		janat[0] = new Line(0,0 , 100,0);
		
		janat[1] = new Line(100,0,100,100);
		janat[2] = new Line(100,100,0,100);
		janat[3] = new Line(0,100,0,0);
		
		// Este
		janat[4] = new Line(35,50 , 70,35);
		janat[5] = new Line(70,35 , 70,60);
		janat[6] = new Line(70,60 , 35,60);
		janat[7] = new Line(35,60 , 35,50);
		
		LineMap kartta = new LineMap(janat, suorakulmio);
		
		ShortestPathFinder polunetsijÃ¤ = new ShortestPathFinder(kartta);
		polunetsijÃ¤.lengthenLines(10);
		
		
		int x = 55, y = 10, d = 180;
		
		Waypoint pisteA = new Waypoint(18, 43);
		Pose poseA = new Pose(18, 43, 0);
		Waypoint pisteB = new Waypoint(71, 80);
		Pose poseB = new Pose(71, 80, 0);
		Waypoint pisteC = new Waypoint(82, 28);
		Pose poseC = new Pose(82, 28, 0);
		Waypoint lopetus = new Waypoint(15, 15);
		
		Pose alkusijainti = new Pose(x, y, d);
		Waypoint alku = new Waypoint(x, y);
		
		Path ekaA = polunetsijÃ¤.findRoute(alkusijainti, pisteA);
		Path AB = polunetsijÃ¤.findRoute(poseA, pisteB);
		Path BC = polunetsijÃ¤.findRoute(poseB, pisteC);
		Path Ceka = polunetsijÃ¤.findRoute(poseC, alku);
		
		
		navi.getPoseProvider().setPose(alkusijainti);
		/*navi.addWaypoint(pisteA);
		navi.addWaypoint(pisteB);
		navi.addWaypoint(pisteC);*/
		
		navi.setPath(ekaA);
		navi.followPath();
		navi.waitForStop();
		navi.getPoseProvider().setPose(poseA);
		
		/*navi.setPath(AB);
		navi.followPath();
		navi.waitForStop();
		navi.getPoseProvider().setPose(poseB);*/
		
		/*navi.setPath(BC);
		navi.followPath();
		navi.waitForStop();
		navi.getPoseProvider().setPose(poseC);
		
		navi.setPath(Ceka);
		navi.followPath();
		navi.waitForStop();*/
		
		/*navi.followPath();
		navi.waitForStop();*/
		
		pilot.stop();

	}
}