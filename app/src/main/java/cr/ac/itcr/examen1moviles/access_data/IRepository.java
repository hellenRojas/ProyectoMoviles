package cr.ac.itcr.examen1moviles.access_data;

import java.util.ArrayList;

import cr.ac.itcr.examen1moviles.entity.Flor;

/**
 * Created by Hellen Rojas Rojas on 30/03/2016.
 */
/*
   Interface de IRepository
 */
public interface IRepository<Object> {
    public boolean Save(Object object);
    public boolean Update(Object object);
    public boolean Delete(Object object);
    public ArrayList<String> GetAll();
    public Flor GetBy(String object);
}
