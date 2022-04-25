
/**
 * Write a description of class Octantes here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class Octante
{
    ArrayList<Abeja> a= new ArrayList<Abeja>();
    double Xmax;
    double Xmin;
    double Ymax;
    double Ymin;
    double Zmax;
    double Zmin;
    Octante padre;
    public Octante(double maxX, double minX,double maxY, double minY,double maxZ, double minZ ){
    Xmax=maxX;
    Xmin=minX;
    Ymax=maxY;
    Ymin=minY;
    Zmax=maxZ;
    Zmin=minZ;
    }
    
  
    
    
}
