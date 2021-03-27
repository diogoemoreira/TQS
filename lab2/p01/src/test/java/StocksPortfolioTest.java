import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

class StocksPortfolioTest {

    @Test
    void getTotalValueWithMocks() {

        //1.Prepare a mock to substitute the remote service (@Mock annotation)
        IStockMarket market = mock(IStockMarket.class);

        //2.Create an instance of the subject under test (SuT) and use the mock to set the (remote) service instance.
        StocksPortfolio portfolio = new StocksPortfolio();
        portfolio.setMarketService(market);
        //3.Load the mock with the proper expectations (when...thenReturn)
        when(market.getPrice("RedChilli")).thenReturn(3.0);
        when(market.getPrice("PaperBrown")).thenReturn(1.0);

        //4.Execute the test (use the service in the SuT)
        portfolio.addStock(new Stock("RedChilli", 3));
        portfolio.addStock(new Stock("PaperBrown", 5));

        //5.Verify the result (assert) and the use of the mock (verify)
        assertEquals(14.0, portfolio.getTotalValue());
        verify(market, times(2)).getPrice(anyString());

        //Hamcrest
        assertThat(portfolio.getTotalValue(), is(14.0));
    }
}