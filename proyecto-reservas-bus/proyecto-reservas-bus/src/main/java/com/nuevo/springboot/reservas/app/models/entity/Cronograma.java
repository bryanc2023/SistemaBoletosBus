package com.nuevo.springboot.reservas.app.models.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Table
@Entity
public class Cronograma{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String descripcion;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

	@DateTimeFormat(pattern="hh:mm:ss")
	private String horaSalida;
	
	public String getHoraSalida() {
		return horaSalida;
	}



	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	private String dia;
	private String mes;
	private int anio;
	
	@ManyToOne
	@JoinColumn(name="id_unidad")
	private Unidad unidad;
	
	@ManyToOne
	@JoinColumn(name="id_ruta")
	private Ruta ruta;
	
	
	@OneToMany(mappedBy = "cronograma",cascade = CascadeType.ALL)
	public List<Asiento> asientos;
	
	
	
	public void forEach(Consumer<? super Asiento> action) {
		asientos.forEach(action);
	}



	public int size() {
		return asientos.size();
	}



	public boolean isEmpty() {
		return asientos.isEmpty();
	}



	public boolean contains(Object o) {
		return asientos.contains(o);
	}



	public Iterator<Asiento> iterator() {
		return asientos.iterator();
	}



	public Object[] toArray() {
		return asientos.toArray();
	}



	public <T> T[] toArray(T[] a) {
		return asientos.toArray(a);
	}



	public boolean add(Asiento e) {
		return asientos.add(e);
	}



	public boolean remove(Object o) {
		return asientos.remove(o);
	}



	public boolean containsAll(Collection<?> c) {
		return asientos.containsAll(c);
	}



	public boolean addAll(Collection<? extends Asiento> c) {
		return asientos.addAll(c);
	}



	public boolean addAll(int index, Collection<? extends Asiento> c) {
		return asientos.addAll(index, c);
	}



	public boolean removeAll(Collection<?> c) {
		return asientos.removeAll(c);
	}



	public boolean retainAll(Collection<?> c) {
		return asientos.retainAll(c);
	}



	public void replaceAll(UnaryOperator<Asiento> operator) {
		asientos.replaceAll(operator);
	}



	public <T> T[] toArray(IntFunction<T[]> generator) {
		return asientos.toArray(generator);
	}



	public void sort(Comparator<? super Asiento> c) {
		asientos.sort(c);
	}



	public void clear() {
		asientos.clear();
	}



	public boolean equals(Object o) {
		return asientos.equals(o);
	}



	public int hashCode() {
		return asientos.hashCode();
	}



	public Asiento get(int index) {
		return asientos.get(index);
	}



	public Asiento set(int index, Asiento element) {
		return asientos.set(index, element);
	}



	public void add(int index, Asiento element) {
		asientos.add(index, element);
	}



	public boolean removeIf(Predicate<? super Asiento> filter) {
		return asientos.removeIf(filter);
	}



	public Asiento remove(int index) {
		return asientos.remove(index);
	}



	public int indexOf(Object o) {
		return asientos.indexOf(o);
	}



	public int lastIndexOf(Object o) {
		return asientos.lastIndexOf(o);
	}



	public ListIterator<Asiento> listIterator() {
		return asientos.listIterator();
	}



	public ListIterator<Asiento> listIterator(int index) {
		return asientos.listIterator(index);
	}



	public List<Asiento> subList(int fromIndex, int toIndex) {
		return asientos.subList(fromIndex, toIndex);
	}



	public Spliterator<Asiento> spliterator() {
		return asientos.spliterator();
	}



	public Stream<Asiento> stream() {
		return asientos.stream();
	}



	public Stream<Asiento> parallelStream() {
		return asientos.parallelStream();
	}



	public Unidad getUnidad() {
		return unidad;
	}



	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}



	public Ruta getRuta() {
		return ruta;
	}



	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}
	

	public List<Asiento> getAsientos() {
		return asientos;
	}



	public void setAsientos(List<Asiento> asientos) {
		this.asientos = asientos;
	}



	public Cronograma() {
		super();
	}



	public Cronograma(Integer id) {
		super();
		this.id = id;
	}

	public Cronograma(String descripcion, LocalDate fecha, String horaSalida, String dia, String mes, int anio,
			Unidad unidad, Ruta ruta, List<Asiento> asientos) {
		super();
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.horaSalida = horaSalida;
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
		this.unidad = unidad;
		this.ruta = ruta;
		this.asientos = asientos;
	}



	public Cronograma(Integer id, String descripcion, LocalDate fecha, String horaSalida, String dia, String mes,
			int anio, Unidad unidad, Ruta ruta, List<Asiento> asientos) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.horaSalida = horaSalida;
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
		this.unidad = unidad;
		this.ruta = ruta;
		this.asientos = asientos;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}
	
	
	
}
