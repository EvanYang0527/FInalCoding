package pkgUT;

import app.LoanResolver;
import javafx.collections.ObservableList;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class TestCalculate {
    @Test
    public void testCalculate(){
        double loanAmount=500;
        double interestRate=60.00*0.01;
        int termOfYears=1;
        LocalDate firstPaymentDate= LocalDate.of(2019,8,1);
        double additionalPayment=0.00;

        double expectedPaymentPerMonth[]={
                56.41, 56.41, 56.41, 56.41, 56.41, 56.41, 56.41, 56.41, 56.41, 56.41, 56.41, 56.41, 0.04
        };
        double expectedInterestPerMonth[]={
                25.00, 23.43, 21.78, 20.05, 18.23, 16.32, 14.32, 12.21, 10.00, 7.68, 5.25, 2.69, 0.00
        };
       double expectedPrincipalPerMonth[]={
               31.41, 32.98, 34.63, 36.36, 38.18, 40.09, 42.09, 44.20, 46.41, 48.73, 51.16, 53.72, 0.04
       };
       double expectedBalancePerMonth[]={
               468.59, 435.61, 400.98, 364.62, 326.44, 286.35, 244.26, 200.06, 153.65, 104.92, 53.76, 0.04, 0.00
       };

       double expectedTotalPayment=676.96;
       double expectedTotalInterest=176.96;
        LoanResolver loanResolver = new LoanResolver();
        ObservableList<LoanResolver.PaymentItem> data
                = loanResolver.CalculatePayment(loanAmount,interestRate,termOfYears,additionalPayment,firstPaymentDate);

        for(int i=1;i<data.size();++i){
            assertEquals(Double.parseDouble(data.get(i).getPayment()),expectedPaymentPerMonth[i-1],0.01);
            assertEquals(Double.parseDouble(data.get(i).getInterest()),expectedInterestPerMonth[i-1],0.01);
            assertEquals(Double.parseDouble(data.get(i).getPrinciple()),expectedPrincipalPerMonth[i-1],0.01);
            assertEquals(Double.parseDouble(data.get(i).getBalance()),expectedBalancePerMonth[i-1],0.01);
        }
        assertEquals(expectedTotalInterest,loanResolver.getTotalInterests(),0.01);
        assertEquals(expectedTotalPayment,loanResolver.getTotalPayments(),0.01);
    }
}
