import java.io.Serializable;
import org.json.JSONObject;

/**
 * Purpose:
 * An class defining json serializable user objects.
 * <p/>
 * Ser321 Principles of Distributed Software Systems
 * @author Tim Lindquist (Tim.Lindquist@asu.edu) CIDSE - Software Engineering
 *                       Ira Fulton Schools of Engineering, ASU Polytechnic
 * @file    User.java
 * @date    January, 2020
 */
class User implements Serializable {

   // Serial version UID is defined below. Its only needed if you want
   // to make changes to the class and still deserialize artifacts
   // generated from prior versions. Obtain this definition with:
   // serialver -classpath classes:lib/json.jar ser321.serialize.User
   private static final long serialVersionUID = 3415902006212375222L; // for backwards compatibility

   private String userId, userPwd; // attributes

   public User(String id, String pwd) { // parameterized constructor using primitives

      userId = id;
      userPwd = pwd;
   }

   public User(JSONObject obj){ // parameterized constructor using json object
      userId = obj.getString("userId");
      userPwd = obj.getString("userPwd");
   }

   public JSONObject toJSONObject(){ // put user into json object format
      JSONObject obj = new JSONObject();
      obj.put("userId",userId);
      obj.put("userPwd",userPwd);
      //System.out.println("User toJSONObject returning: "+obj.toString());
      return obj;
   }

   protected String getId() {
      return userId;
   }

   protected boolean check(String id, String pwd) {
      return (userId.equals(id) && userPwd.equals(pwd));
   }

}
    
