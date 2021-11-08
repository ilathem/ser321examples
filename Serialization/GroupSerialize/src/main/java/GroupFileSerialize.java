import java.io.File;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.util.Vector;
import java.util.Enumeration;

/**
 * Purpose:
 * An interface defining operations for managing authentication groups.
 * <p/>
 * Ser321 Principles of Distributed Software Systems
 * @author Tim Lindquist (Tim.Lindquist@asu.edu) CIDSE - Software Engineering
 *                       Ira Fulton Schools of Engineering, ASU Polytechnic
 * @file    GroupFileSerialize.java
 * @date    January, 2020
 */
public class GroupFileSerialize {
  public static void main (String args[]) {

    try {
      // create a group
      Group admin = new GroupImpl("Administration");
      admin.addUserToGroup("Tim","any");
      admin.addUserToGroup("Joe","hisWord");
      admin.addUserToGroup("Sue","herWord");

      // save to file as defined in group implemenation (save all users to file)
      admin.saveToFile();
      
      // we could use a socket output stream instead of a file output stream
      System.out.println("Server ready and waiting to export a group");

      // read in what we just outputted
      File outFile = new File("admin.ser");
      ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(outFile));
      os.writeObject(admin);
      os.flush();
      os.close(); //no need in Java to close the File outFiel (not a closeable object).
      System.out.println("Server done exporting a group");

      File inFile = new File("admin.ser");
      ObjectInputStream in =
                            new ObjectInputStream(new FileInputStream(inFile));
      Group g = (GroupImpl)in.readObject();
      System.out.println("Group "+g.getName()+" received. Includes:");
      Vector<String> users = g.getUserNames();
      for (Enumeration e = users.elements(); e.hasMoreElements() ;) {
        System.out.println((String)e.nextElement());
      }
      in.close();
    }catch(Exception e) {
      e.printStackTrace();
    }
  }
}
