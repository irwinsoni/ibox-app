package edu.csupomona.cs585.ibox;

import static org.mockito.Mockito.*;

import java.io.File;

import org.junit.*;
import org.mockito.Mockito;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.Drive.Files.List;
import com.google.api.services.drive.model.FileList;

import edu.csupomona.cs585.ibox.sync.FileSyncManager;
import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;
import java.io.IOException;
import java.util.ArrayList;

public class GoogleDriveFileSyncManagerTest {
	private Drive mockDriveService;
	private GoogleDriveFileSyncManager googleDriveFileSyncManager;

	@Before
	public void setup() {
		mockDriveService = mock(Drive.class);
		googleDriveFileSyncManager = new GoogleDriveFileSyncManager(mockDriveService);
	}

	@Test
	public void addFileTest() throws IOException {
		File newFile = new File("E://test.txt");
		com.google.api.services.drive.model.File value = new com.google.api.services.drive.model.File();
		// com.google.api.services.drive.model.File
		// mockFile=mock(com.google.api.services.drive.model.File.class);
		value.setTitle(newFile.getName());
		value.setId("test");
		Files newFiles = mock(Files.class);
		Files.Insert insert = mock(Files.Insert.class);
		when(mockDriveService.files()).thenReturn(newFiles);
		when(newFiles.insert(Mockito.any(com.google.api.services.drive.model.File.class),
				Mockito.any(AbstractInputStreamContent.class))).thenReturn(insert);
		when(insert.execute()).thenReturn(value);
		googleDriveFileSyncManager.addFile(newFile);
		verify(insert).execute();
	}

	@Test(expected = IOException.class)
	public void testAddFileIOException() throws IOException {
		File file = new File("E://test.txt");
		com.google.api.services.drive.model.File value = new com.google.api.services.drive.model.File();
		// com.google.api.services.drive.model.File
		// mockFile=mock(com.google.api.services.drive.model.File.class);
		value.setTitle(file.getName());
		value.setId("test");
		Files files = mock(Files.class);
		Files.Insert insert = mock(Files.Insert.class);
		when(mockDriveService.files()).thenReturn(files);
		when(files.insert(Mockito.any(com.google.api.services.drive.model.File.class),
				Mockito.any(AbstractInputStreamContent.class))).thenReturn(insert);
		when(insert.execute()).thenThrow(new IOException());
		googleDriveFileSyncManager.addFile(file);
	}

	@Test
	public void testGetField() throws IOException {
		com.google.api.services.drive.model.File file = new com.google.api.services.drive.model.File();
		file.setTitle("text");
		file.setId("123");
		String filename = "text";
		FileList newFilelist = new FileList();
		ArrayList<com.google.api.services.drive.model.File> arraylist = new ArrayList<>();
		arraylist.add(file);
		newFilelist.setItems(arraylist);
		Files files = mock(Files.class);
		List list = mock(List.class);
		when(mockDriveService.files()).thenReturn(files);
		when(files.list()).thenReturn(list);
		when(list.execute()).thenReturn(newFilelist);
		String result = googleDriveFileSyncManager.getFileId("text");
		Assert.assertEquals("123", result);
	}

	@Test

	public void updateFileTest() throws IOException {
		File localFile = new File("E://test.txt");
		com.google.api.services.drive.model.File file = new com.google.api.services.drive.model.File();
		file.setTitle("test.txt");
		file.setId("123");
		String filename = "test.txt";
		FileList filelist = new FileList();
		java.util.List<com.google.api.services.drive.model.File> arraylist = new ArrayList<>();
		arraylist.add(file);
		filelist.setItems(arraylist);
		Files files = mock(Files.class);
		List list = mock(List.class);
		Files.Update update = mock(Files.Update.class);
		// when(localFile.getName()).thenReturn(filename);
		when(mockDriveService.files()).thenReturn(files);
		when(files.list()).thenReturn(list);
		when(list.execute()).thenReturn(filelist);
		when(files.update(Mockito.any(String.class), Mockito.any(com.google.api.services.drive.model.File.class),
				Mockito.any(AbstractInputStreamContent.class))).thenReturn(update);
		when(update.execute()).thenReturn(file);
		googleDriveFileSyncManager.updateFile(localFile);
		verify(update).execute();

	}


	@Test
	public void deleteFileTest() throws IOException {
		File localFile = new File("E://test.txt");
		com.google.api.services.drive.model.File file = new com.google.api.services.drive.model.File();
		file.setTitle("test.txt");
		file.setId("123");
		String filename = "test.txt";
		FileList newFilelist = new FileList();
		java.util.List<com.google.api.services.drive.model.File> arraylist = new ArrayList<>();
		arraylist.add(file);
		newFilelist.setItems(arraylist);
		Files files = mock(Files.class);
		List list = mock(List.class);
		Files.Delete delete = mock(Files.Delete.class);
		when(localFile.getName()).thenReturn(filename);
		when(mockDriveService.files()).thenReturn(files);
		when(files.list()).thenReturn(list);
		when(list.execute()).thenReturn(newFilelist);
		when(files.delete(Mockito.any(String.class))).thenReturn(delete);
		googleDriveFileSyncManager.deleteFile(localFile);
		verify(delete).execute();
	}

	@Test(expected = IOException.class)

	public void deleteFileTest1() throws IOException {
		File newLocalFile = new File("E://test.txt");
		com.google.api.services.drive.model.File file = new com.google.api.services.drive.model.File();
		file.setTitle("test.txt");
		file.setId("123");
		String filename = "test.txt";
		FileList filelist = new FileList();
		java.util.List<com.google.api.services.drive.model.File> arraylist = new ArrayList<>();
		arraylist.add(file);
		filelist.setItems(arraylist);
		Files files = mock(Files.class);
		List list = mock(List.class);
		Files.Delete delete = mock(Files.Delete.class);
		when(newLocalFile.getName()).thenReturn(filename);
		when(mockDriveService.files()).thenReturn(files);
		when(files.list()).thenReturn(list);
		when(list.execute()).thenReturn(filelist);
		when(files.delete(Mockito.any(String.class))).thenThrow(new IOException());
		googleDriveFileSyncManager.deleteFile(newLocalFile);
		verify(delete).execute();
	}
}
