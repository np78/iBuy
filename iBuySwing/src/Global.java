import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.DropboxFileInfo;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.WebAuthSession;
import com.dropbox.client2.session.Session.AccessType;


public class Global {
	public static String APIKey = "nn7lnxubh1knv63";
	public static String APISecret = "l02f3ekx7lc7wx2";
	public static AccessType ACCESS_TYPE = AccessType.APP_FOLDER;
	public static String sessionKey = "wiaqh0zpg745z2v";
	public static String sessionSecret = "5elro586b19u7n2";
	
	public static void makeFolder(DropboxAPI<WebAuthSession> mDBApi, String name, String filename, String contents)
	{
		try
		{
			mDBApi.createFolder(name);
	    	ByteArrayInputStream inputStream = new ByteArrayInputStream(contents.getBytes());
	    	mDBApi.putFile(filename, inputStream, contents.length(), null, null);
		}
		catch (DropboxException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void makeFolder(DropboxAPI<WebAuthSession> mDBApi, String name)
	{
		try {
			mDBApi.createFolder(name);
		} catch (DropboxException e) {
			e.printStackTrace();
		}
	}
	
	//Gets contents of file
	public static String getFile(DropboxAPI<WebAuthSession> mDBApi, String filename)
	{
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
			DropboxFileInfo info = mDBApi.getFile(filename, null, outputStream, null);
		} catch (DropboxException e) {}
		String st = new String(outputStream.toByteArray());
		return st;
	}
	
	//Writes new file with contents
	public static void putFile(DropboxAPI<WebAuthSession> mDBApi, String filename, String contents)
	{
		ByteArrayInputStream inputStream = new ByteArrayInputStream(contents.getBytes());
    	try {
			mDBApi.putFile(filename, inputStream, contents.length(), null, null);
		} catch (DropboxException e) {
			e.printStackTrace();
		}
	}
	
	//Overwrites contents of file
	public static void putFileOverwrite(DropboxAPI<WebAuthSession> mDBApi, String filename, String contents)
	{
		ByteArrayInputStream inputStream = new ByteArrayInputStream(contents.getBytes());
    	try {
			mDBApi.putFileOverwrite(filename, inputStream, contents.length(), null);
		} catch (DropboxException e) {
			e.printStackTrace();
		}
	}
	
	//Replaces spaces with underscores
	public static String toFileName(String st)
	{
		String re = "";
		for(int i = 0; i < st.length(); i++)
		{
			if(st.charAt(i) == ' ')
				re += "_";
			else
				re += st.charAt(i);
		}
		return re;
	}
	
	//Replaces underscores of file name with spaces
	public static String readFileName(String st)
	{
		String re = "";
		for(int i = 0; i < st.length(); i++)
		{
			if(st.charAt(i) == '_')
				re += " ";
			else
				re += st.charAt(i);
		}
		return re;
	}
	
	public static void delete(DropboxAPI<WebAuthSession> mDBApi, String path)
	{
		try {
			mDBApi.delete(path);
		} catch (DropboxException e1) {
			e1.printStackTrace();
		}
	}
	
	//Create session
	public static DropboxAPI<WebAuthSession> createSession()
	{
	    AppKeyPair appKeyPair = new AppKeyPair(APIKey, APISecret);
	    WebAuthSession session = new WebAuthSession(appKeyPair, ACCESS_TYPE);
	    DropboxAPI<WebAuthSession> mDBApi = new DropboxAPI<WebAuthSession>(session);
	    mDBApi.getSession().setAccessTokenPair(new AccessTokenPair(sessionKey, sessionSecret));
	    return mDBApi;
	}
}
