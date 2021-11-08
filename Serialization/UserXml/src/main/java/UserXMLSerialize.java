import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;

/**

 * Purpose:
 * An class defining xml serializable user objects as java beans.
 * For a Java class to be XML serializable, it must have a parameterless
 * constructor and it must have get and set methods for each instance
 * variable in the class.
 * <p/>
 * Ser321 Principles of Distributed Software Systems
 * @author Tim Lindquist (Tim.Lindquist@asu.edu) CIDSE - Software Engineering
 *                       Ira Fulton Schools of Engineering, ASU Polytechnic
 * @file    UserXMLSerialize.java
 * @date    August, 2020
 
 */
public class UserXMLSerialize {
    public static void main (String args[]) {
        try {
            User user = new User("myId", "myPwd", "I", "AM");

            System.out.println("Ready to export a user");
            FileOutputStream xmlos = new FileOutputStream("user.xml"); // we want to serialize to a file
            XMLEncoder encoder = new XMLEncoder(xmlos); // using an xml encoder
            encoder.writeObject(user); // encode the user object to xml (serialize)
            encoder.close(); // cleanpup
            System.out.println("Done exporting a user as xml to user.xml");

            // go the reverse way
            System.out.println("Importing a user as xml from user.xml");
            FileInputStream inFileStream = new FileInputStream("user.xml"); // import the file
            XMLDecoder decoder = new XMLDecoder(inFileStream); // decode the xml file
            User newUser = (User)decoder.readObject(); // deserialize
            System.out.println("Read user: "+ newUser.getFirst()+" "
                               +newUser.getLast());
            decoder.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}

