package com.example.fjob.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.fjob.Entity.usermessage.CpnMessage;

import java.util.List;


@Dao
public interface EditmsgDao {
    @Insert
    public void insertmsg(CpnMessage... cpnMessages);
    @Query("DELETE FROM CPNMESSAGE")
    void deleteAll();
    @Query("SELECT * FROM  CPNMESSAGE  ORDER BY ID DESC")
    List<CpnMessage> list();
}
