
/**
 * Write a description of class Datos here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;
public class Datos
{
    public  ArrayList <Abeja>abejas=new ArrayList<Abeja>();
    public File f;
    Octante[] octantes=new Octante[8];
    public void leerYGuardarDatos(){
        try{
            f=new File("datos.txt");    
            Scanner input=new Scanner(f);
            while(input.hasNextDouble()||input.hasNextFloat()){
                double x=input.nextDouble();
                double y=input.nextDouble();
                double z=input.nextDouble();
                Abeja a=new Abeja(x,y,z);
                abejas.add(a);

            }
        }
        catch(FileNotFoundException e){
            System.out.println("el archivo no existe");
        }

    }

    public void detectarColisiones(){
        double Xmax=abejas.get(0).x;double Xmin=abejas.get(0).x;
        double Ymax=abejas.get(0).y;double Ymin=abejas.get(0).y;
        double Zmax=abejas.get(0).z;double Zmin=abejas.get(0).z;   
        for(int i=0;i<abejas.size();i++){
            double x=abejas.get(i).x;
            double y=abejas.get(i).y;
            double z=abejas.get(i).z;

            if(x>Xmax){
                Xmax=x;
            }
            if(y>Ymax){
                Ymax=y;
            }
            if(z>Zmax){
                Zmax=z; 
            }
            if(x<Xmin){
                Xmin=x;
            }
            if(y<Ymin){
                Ymin=y;
            }
            if(z<Zmin){
                Zmin=z;
            }     
        }
        Octante principal=new Octante(Xmax,Xmin,Ymax,Ymin,Zmax,Zmin);
        principal.a=abejas;
        principal.padre=null;
        
        separacionOctantes(principal);
    }

    private void separacionOctantes(Octante o){
        double maxX=o.Xmax;double minX=o.Xmin;
        double maxY=o.Ymax;double minY=o.Ymin;
        double maxZ=o.Zmax;double minZ=o.Zmin;

        double Xmitad=Math.abs(maxX-minX)/2+minX;
        double Ymitad=Math.abs(maxY-minY)/2+minY;
        double Zmitad=Math.abs(maxZ-minZ)/2+minZ;
        boolean entro=false;

        if(Math.sqrt((maxX-minX)*(maxX-minX)+(maxY-minY)*(maxY-minY)+(maxZ-minZ)*(maxZ-minZ))<=100&&entro==false||o.a.size()<=1){
            if(o.a.size()>=2){
                for(int j=0; j<o.a.size();j++){
                    Abeja a=o.a.get(j);
                    System.out.println("La abeja con coordenadas "+a.x+","+a.y+","+a.z+" esta en riesgo de colision");

                }
                
            }
            else if(o.a.size()==1){
                Abeja a1= o.a.get(0);
                if(o.padre!=null){
                    for(int i=0; i<o.padre.a.size(); i++){
                        Abeja a2=o.padre.a.get(i);
                        if(Math.sqrt((a1.x-a2.x)*(a1.x-a2.x)+(a1.y-a2.y)*(a1.y-a2.y)+(a1.z-a2.z)*(a1.z-a2.z))<=100&&Math.sqrt((a1.x-a2.x)*(a1.x-a2.x)+(a1.y-a2.y)*(a1.y-a2.y)+(a1.z-a2.z)*(a1.z-a2.z))!=0){
                            System.out.println("La abeja con coordenadas "+a1.x+","+a1.y+","+a1.z+" esta en riesgo de colision");
                            break;
                        }
                        
                    }
                
                }
      
            }
            else{
            
            }
            entro=true;
        }
        
        else{
            Octante s1=new Octante(maxX,Xmitad,maxY,Ymitad, maxZ,Zmitad);//+,+,+
            Octante s2=new Octante(Xmitad,minX,maxY,Ymitad, maxZ,Zmitad);//-,+,+
            Octante s3=new Octante(Xmitad,minX,Ymitad,minY, maxZ,Zmitad);//-,-,+
            Octante s4=new Octante(maxX,Xmitad,Ymitad,minY, maxZ,Zmitad);//+,-,+
            Octante s5=new Octante(maxX,Xmitad,maxY,Ymitad, Zmitad,minZ);//+,+,-
            Octante s6=new Octante(Xmitad,minX,maxY,Ymitad, Zmitad,minZ);//-,+,-
            Octante s7=new Octante(Xmitad,minX,Ymitad,minY, Zmitad,minZ);//-,-,-
            Octante s8=new Octante(maxX,Xmitad,Ymitad,minY, Zmitad,minZ);//+,-,-
            s1.padre=o;s2.padre=o;s3.padre=o;s4.padre=o;s5.padre=o;s6.padre=o;s7.padre=o;s8.padre=o;

            for(int i=0; i<o.a.size();i++){
                Abeja a1= o.a.get(i);
                double x=a1.x;
                double y=a1.y;
                double z=a1.z;
                if(x>Xmitad&& y>Ymitad &&z>Zmitad){
                    s1.a.add(a1);
                }
                if(x<Xmitad&& y>Ymitad &&z>Zmitad){
                    s2.a.add(a1);
                }
                if(x<Xmitad&& y<Ymitad &&z>Zmitad){
                    s3.a.add(a1);
                }
                if(x>Xmitad&& y<Ymitad &&z>Zmitad){
                    s4.a.add(a1);
                }
                if(x>Xmitad&& y>Ymitad &&z<Zmitad){
                    s5.a.add(a1);
                }
                if(x<Xmitad&& y>Ymitad &&z<Zmitad){
                    s6.a.add(a1);
                }
                if(x<Xmitad&& y<Ymitad &&z<Zmitad){
                    s7.a.add(a1);
                }
                if(x>Xmitad&& y<Ymitad &&z<Zmitad){
                    s8.a.add(a1);
                }
            }
            separacionOctantes(s1);
            separacionOctantes(s2);
            separacionOctantes(s3);
            separacionOctantes(s4);
            separacionOctantes(s5);
            separacionOctantes(s6);
            separacionOctantes(s7);
            separacionOctantes(s8);

        }
    }
}

