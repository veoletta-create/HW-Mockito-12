package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PosterManagerTest {

    private String[] testFilms;

    @BeforeEach
    void setUp() {
        testFilms = new String[] {
                "Номер один",
                "Тролли. Мировой тур",
                "Человек-невидимка",
                "Джентльмены",
                "Отель Белград",
                "Вперед",
                "Бладшот"
        };
    }

    @Test
    void emptyManager() {
        PosterManager manager = new PosterManager();
        String[] actual = manager.findAll();
        String[] expected = new String[0];

        assertArrayEquals(expected, actual);
    }

    @Test
    void testDefaultLimitConstructor() {
        PosterManager manager = new PosterManager();
        int actual = manager.getLimit();
        int expected = 5;

        assertEquals(expected, actual);
    }

    @Test
    void limitConstructor() {
        int actual1 = new PosterManager(3).getLimit();
        int expected1 = 3;

        int actual2 = new PosterManager(7).getLimit();
        int expected2 = 7;

        int actual3 = new PosterManager(10).getLimit();
        int expected3 = 10;

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);
    }

    @Test
    void testAddFilm() {
        PosterManager manager = new PosterManager();
        manager.addFilm("Номер один");
        String[] actual = manager.findAll();
        String[] expected = {"Номер один"};

        assertArrayEquals(expected, actual);
    }

    @Test
    void testAddAllFilms() {
        PosterManager manager = new PosterManager();
        for (String film : testFilms) {
            manager.addFilm(film);
        }
        String[] actual = manager.findAll();
        String[] expected = testFilms;

        assertArrayEquals(expected, actual);
    }

    @Test
    void defaultLimit() {
        PosterManager manager = new PosterManager();
        for (String film : testFilms) {
            manager.addFilm(film);
        }
        String[] actual = manager.findLast();
        String[] expected = {"Бладшот", "Вперед", "Отель Белград", "Джентльмены", "Человек-невидимка"};

        assertArrayEquals(expected, actual);
    }

    @Test
    void Limit3() {
        PosterManager manager = new PosterManager(3);
        for (String film : testFilms) {
            manager.addFilm(film);
        }
        String[] actual = manager.findLast();
        String[] expected = {"Бладшот", "Вперед", "Отель Белград"};

        assertArrayEquals(expected, actual);
    }

    @Test
    void Limit7() {
        PosterManager manager = new PosterManager(7);
        for (String film : testFilms) {
            manager.addFilm(film);
        }
        String[] actual = manager.findLast();
        String[] expected = {"Бладшот", "Вперед", "Отель Белград", "Джентльмены",
                "Человек-невидимка", "Тролли. Мировой тур", "Номер один"};

        assertArrayEquals(expected, actual);
    }

    @Test
    void lessFilmsThanLimit() {
        PosterManager manager = new PosterManager(10);
        manager.addFilm("Фильм 1");
        manager.addFilm("Фильм 2");
        int actual = manager.findLast().length;
        int expected = 2;

        String[] actual2 = manager.findLast();
        String[] expected2 = {"Фильм 2", "Фильм 1"};

        assertEquals(expected, actual);
        assertArrayEquals(expected2, actual2);
    }

    @Test
    void emptyFindLast() {
        PosterManager manager = new PosterManager();
        int actual = manager.findLast().length;
        int expected = 0;

        assertEquals(expected, actual);
    }
}