package com.sddevops.jenkins_maven.eclipse;


import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sddevops.sonarqube_maven.eclipse.Song;
import com.sddevops.sonarqube_maven.eclipse.SongCollection;

class SongColletionTest {
	
	private SongCollection sc;
	private Song s1;
	private Song s2;
	private Song s3;
	private Song s4;
	private Song s5;
	private final int SONG_COLLECTION_SIZE=4;

	@BeforeEach
	void setUp() throws Exception {
		//Arrange
		sc=new SongCollection();
		s1 = new Song("001","good 4 u","Olivia Rodrigo",3.59);
		s2 = new Song("002","Peaches","Justin Bieber",3.18);
		s3 = new Song("003","MONTERO","Lil Nas",2.3);
		s4 = new Song("004","bad guy","billie eilish",3.14);
		sc.addSong(s1);
		sc.addSong(s2);
		sc.addSong(s3);
		sc.addSong(s4);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetSongs() {
	    List<Song> testSc = sc.getSongs();

	    // Assert that the Song Collection is not null
	    assertNotNull(testSc);

	    // Assert that the size of the Song Collection matches the expected size
	    assertEquals(testSc.size(), SONG_COLLECTION_SIZE);

	    // Assert that the Song Collection contains all the added songs
	    assertTrue(testSc.contains(s1));
	    assertTrue(testSc.contains(s2));
	    assertTrue(testSc.contains(s3));
	    assertTrue(testSc.contains(s4));
	}

	@Test
	void testAddSong() {
	    List<Song> testSc = sc.getSongs();

	    // Assert that Song Collection is equals to Song Collection Size : 4
	    assertEquals(testSc.size(), SONG_COLLECTION_SIZE);

	    // Act
	    Song newSong = new Song("006", "New Song", "New Artist", 4.15);
	    sc.addSong(newSong);

	    // Assert that Song Collection size increases by 1 after adding a new song
	    assertEquals(testSc.size(), SONG_COLLECTION_SIZE + 1);

	    // Assert that the newly added song is in the Song Collection
	    assertTrue(testSc.contains(newSong));
	}

	@Test
	void testSortSongsByTitle() {
		  List<Song> sortedSongs = sc.sortSongsByTitle();
	        for (int i = 1; i < sortedSongs.size(); i++) {
	            String prevTitle = sortedSongs.get(i - 1).getTitle();
	            String currTitle = sortedSongs.get(i).getTitle();
	            assertTrue(prevTitle.compareTo(currTitle) <= 0, "Song titles are not sorted correctly.");
	        }
	}

	@Test
	void testSortSongsBySongLength() {
	    // Act
	    List<Song> sortedByLength = sc.sortSongsBySongLength();

	    // Assert that the sorted list is not null
	    assertNotNull(sortedByLength);

	    // Assert that the sorted list has the same number of songs as the original list
	    assertEquals(sortedByLength.size(), SONG_COLLECTION_SIZE);

	    // Assert that the songs in the sorted list are indeed sorted by length
	    for (int i = 1; i < sortedByLength.size(); i++) {
	        double currentLength = sortedByLength.get(i).getSongLength();
	        double previousLength = sortedByLength.get(i - 1).getSongLength();
	        assertTrue(currentLength < previousLength);
	    }
	}

	@Test
	void testFindSongsById() {
        Song foundSong = sc.findSongsById("002");
        assertNotNull(foundSong, "Song with ID '002' should be found.");
        assertEquals(s2, foundSong, "Found song does not match expected song.");
	}

	@Test
	void testFindSongByTitle() {
		Song foundSong = sc.findSongByTitle("Peaches");
        assertNotNull(foundSong, "Song with title 'Peaches' should be found.");
        assertEquals(s2, foundSong, "Found song does not match expected song.");
	}
	

}
