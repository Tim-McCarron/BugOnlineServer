package Unit;

/**
 *
 * @author Able
 */
// absolute unit
public class Unit {
    private double x;
    private double y;
    private double z;
    private boolean airborne;
    private float direction;
    private String name;
    private String spritePath;
    private String id;
    private double speed;
//    private final static boolean ABSOLUTE = true;
    public Unit() {}
    public Unit(double x, double y, double z, float dir, String name, String spritePath, String id, double speed) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.direction = dir;
        this.name = name;
        this.spritePath = spritePath;
        this.speed = speed;
        this.id = id;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public double getZ() {
        return z;
    }
    
    public String getName() {
        return name;
    }
    
    public String getSprite() {
        return spritePath;
    }
    
    public boolean getIsAirborne() {
        return airborne;
    }
    
    public String getId() {
        return id;
    }
    
    public double getSpeed() {
        return speed;
    }
    
    public void setX(double x) {
        this.x = x;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    public void setZ(double z) {
        this.z = z;
    }
    
    public void setDir(float d) {
        this.direction = d;
    }
    
    public float getDir() {
        return this.direction;
    }
    
    public void setIsAirborne(boolean ab) {
        this.airborne = ab;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setSprite(String spritePath) {
        this.spritePath = spritePath;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    
}
