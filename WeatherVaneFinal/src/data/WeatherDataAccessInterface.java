package data;

import java.util.List;

/**
 * @Author Jerran Fredricks/Joe Lean
 * 10/21/2018
 **/
public interface WeatherDataAccessInterface <T>{
	public List<T> findAll();

	public T findBy(T t);

	public boolean create(T t);

	public boolean update(T t);

	public boolean delete(T t);

	public List<T> findByDate(String str);

	public T findById(int id);
}
