import java.util.ArrayList;

public class StocksPortfolio{
    IStockMarket marketService;
    String name;
    ArrayList<Stock> stocks = new ArrayList<Stock>();

    public StocksPortfolio(){}

    public Double getTotalValue(){
        Double value = 0.0;
        for(Stock s: stocks){
            value+=marketService.getPrice(s.getName())*s.getQuantity();
        }
        return value;
    }

    public void addStock(Stock stock){
        stocks.add(stock);
    }

    public IStockMarket getMarketService() {
        return marketService;
    }

    public void setMarketService(IStockMarket marketService) {
        this.marketService = marketService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
