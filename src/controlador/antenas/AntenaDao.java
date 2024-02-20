/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.antenas;

import controlador.grafos.GrafoEtiquetadoNoDirigido;
import controlador.listas.ListaEnlazada;
import controlador.dao.DataAccessObject;
import java.io.FileOutputStream;
import java.io.FileReader;
import modelo.Antena;

/**
 *
 * @author Apolo
 */
public class AntenaDao extends DataAccessObject<Antena>{
    
    public AntenaDao(Class<Antena> clazz) {
        super(clazz);
    }
    private Antena antena = new Antena();
    private ListaEnlazada<Antena> antenas = new ListaEnlazada<>();
    private GrafoEtiquetadoNoDirigido<Antena> grafoAntena;
    
    private Integer index = -1;

    public AntenaDao() {
        super(Antena.class);
    }   
    
    public GrafoEtiquetadoNoDirigido<Antena> getGrafoAntena() throws Exception{
        if(grafoAntena == null){
            ListaEnlazada<Antena> lista = getAntenas();
            Integer size = listAll().getSize();
            if(size > 0){
                grafoAntena = new GrafoEtiquetadoNoDirigido<>(size,Antena.class);
                for (int i = 0; i < size; i++) {
                    grafoAntena.etiquetarVertice(i + 1, lista.get(i));
                }
            }
        }
        return grafoAntena;
    }

    public Antena getAntena() {
        if (antena == null) {
            antena = new Antena();
        }
        return antena;
    }

    public void setAntena(Antena antena) {
        this.antena = antena;
    }

    public Boolean guardar() {
        antena.setId(generated_ID());
        return save(antena);
    }

    public Boolean update(int fila) {
        return update(antena, fila);
    }

    public ListaEnlazada<Antena> getAntenas() {
        if (antenas.isEmpty()) {
            antenas = listAll();
        }
        return antenas;
    }

    public void setAntenas(ListaEnlazada<Antena> antenas) {
        this.antenas = antenas;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
    public Boolean update() {
        return update(antena, buscarIndex(antena.getId()));
    }
    public Integer buscarIndex(Integer id){
        Integer index = -1;
        if(!listAll().isEmpty()){
            Antena [] antenas = listAll().toArray();
            for (int i = 0; i < antenas.length; i++) {
                if(id.intValue() == antenas[i].getId().intValue()) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }
    public void guardarGrafo() throws Exception{
        if (grafoAntena != null){
            getXstream().alias(grafoAntena.getClass().getName(), GrafoEtiquetadoNoDirigido.class);
            getXstream().toXML(grafoAntena, new FileOutputStream("data/grafo.json"));
        } else new NullPointerException("Grafo Vacio");
        
    }
    public void cargarGrafo() throws Exception{
        grafoAntena = (GrafoEtiquetadoNoDirigido<Antena>)getXstream().
                fromXML(new FileReader("data/grafo.json"));  
        antenas.clear();
        for (int i = 1 ; i <= grafoAntena.nro_vertices(); i++) {
            antenas.add(grafoAntena.obtenerEtiquetaE(i));
            System.out.println("");
        }
        
    }
    
    
}
