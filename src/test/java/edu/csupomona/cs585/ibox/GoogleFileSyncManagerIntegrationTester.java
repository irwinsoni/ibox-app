//package edu.csupomona.cs585.ibox;
//
//import static org.mockito.Mockito.*;
//
//import java.io.File;
//
//import org.junit.*;
//import org.mockito.Mockito;
//
//import com.google.api.client.http.AbstractInputStreamContent;
//import com.google.api.client.http.FileContent;
//import com.google.api.services.drive.Drive;
//import com.google.api.services.drive.Drive.Files;
//import com.google.api.services.drive.Drive.Files.List;
//import com.google.api.services.drive.model.FileList;
//
//import edu.csupomona.cs585.ibox.sync.FileSyncManager;
//import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;
//import edu.csupomona.cs585.ibox.sync.GoogleDriveServiceProvider;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class GoogleFileSyncManagerIntegrationTester {
//	
//	GoogleDriveFileSyncManager object = new GoogleDriveFileSyncManager(GoogleDriveServiceProvider.get().getGoogleDriveClient());;
//	
//	@Test
//	public void testAdd() throws IOException{
//		File localFile = new File("C:/Users/A/Desktop/project.txt");
//		object.addFile(localFile );
//	}
//	
//	@Test
//	public void testUpdate() throws IOException{
//		File localFile = new File("C:/Users/A/Desktop/project.txt");
//		object.updateFile(localFile);
//		
//	}
//	
//	@Test
//	public void testDelete() throws IOException{
//		File localFile = new File("C:/Users/A/Desktop/project.txt");
//		object.deleteFile(localFile);
//		
//	}
//	
//
//}
