package com.caiojacintho.junit.dominio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MangaTest {
    private Manga manga1;
    private Manga manga2;
    @BeforeEach
    void setUp() {
        manga1 = new Manga("Teste1",20);
        manga2 = new Manga("Teste1",20);
    }


    @Test
    public void acessors_ReturnData_WhenInitialized(){
        Assertions.assertEquals("Teste1",manga1.name());
        Assertions.assertEquals(20,manga1.episodes());
    }

    @Test
    public void equals_ReturnTrue_WhenObjectsAreTheSame(){
        Assertions.assertEquals(manga1,manga2);
    }

    @Test
    public void hashCode_ReturnTrue_WhenObjectsAreTheSame(){
        Assertions.assertEquals(manga1.hashCode(),manga2.hashCode());
    }

    @Test
    public void construtor_ThrowNullPointerException_WhenNameIsNull(){
        Assertions.assertThrows(NullPointerException.class, () -> new Manga(null,20));
    }

    @Test
    public void isRecord_ReturnTrue_WhenCalledFromManga(){
        Assertions.assertTrue(Manga.class.isRecord());
    }


}