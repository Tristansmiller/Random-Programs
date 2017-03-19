//This class is a custom exception to be thrown when a projectile or enemy collides with the player.
public class CollisionException extends RuntimeException {

	public CollisionException(){}
	public CollisionException(String s){ System.out.println(s);}
}
