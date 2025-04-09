package Datasets;

public interface DataLoader<T>
{
    T loadData(String path) throws Exception;
}
