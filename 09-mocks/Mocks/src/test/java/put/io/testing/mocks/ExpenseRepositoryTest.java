package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;
import java.util.List;

public class ExpenseRepositoryTest {
    private ExpenseRepository expenseRepository;
    private List<Expense> expenses;
    private IFancyDatabase fancyDatabase;

//    @BeforeEach
//    void setup()
//    {
//        expenseRepository = new ExpenseRepository(fancyDatabase);
//        //fancyDatabase = new FancyDatabase();
//    }

//    @Test
//    void testLoadExpenses() {
//        fancyDatabase.connect();
//        expenses = new ArrayList<Expense>(fancyDatabase.<Expense>queryAll());
//    }

    /*
        Pytanie 2.1:
        Należy wykorzystać obiekt inOrder
    */

    @Test
    void testLoadExpenses()
    {
        IFancyDatabase mockDatabase = mock(IFancyDatabase.class);
        when(mockDatabase.queryAll()).thenReturn(Collections.emptyList());

        expenseRepository = new ExpenseRepository(mockDatabase);
        expenseRepository.loadExpenses();

        InOrder inOrder = inOrder(mockDatabase);
        inOrder.verify(mockDatabase).connect();
        inOrder.verify(mockDatabase).queryAll();
        inOrder.verify(mockDatabase).close();


        assertTrue(expenseRepository.getExpenses().isEmpty());
    }


    @Test
    void testGetExpenses()
    {
        assertTrue(expenseRepository.getExpenses().isEmpty());
    }
}
