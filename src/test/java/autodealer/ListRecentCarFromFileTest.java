package autodealer;

import static org.mockito.Mockito.*;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import GetFileInfo.GetRecentCar;
import GetFileInfo.ListRecentCarFromFile;

public class ListRecentCarFromFileTest {

    @Test
    public void testDisplayRecentCars() throws FileNotFoundException, ParseException {
        GetRecentCar recentCarMock = mock(GetRecentCar.class);

        List<String[]> simulatedRecentCars = new ArrayList<>();
        simulatedRecentCars.add(new String[]{"ABC123", "Toyota", "Corolla", "2022-01-01", "20000"});
        simulatedRecentCars.add(new String[]{"XYZ456", "Honda", "Civic", "2022-02-15", "25000"});

        when(GetRecentCar.getRecentCars()).thenReturn(simulatedRecentCars);

        ListRecentCarFromFile.setRecentCarInstance(recentCarMock);

        ListRecentCarFromFile.displayRecentCars();
        verify(recentCarMock, times(1)).getRecentCars();
    }
}



