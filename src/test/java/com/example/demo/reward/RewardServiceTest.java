package com.example.demo.reward;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

public class RewardServiceTest {
	@Test
	void calculateMonthlyRewards() {
		RewardService service = new RewardService();
		
		List<TransactionDto> transactions = new ArrayList<TransactionDto>();
		transactions.add(new TransactionDto("user1",120.0,toDate("2020-01-02")));
		transactions.add(new TransactionDto("user1",90.0,toDate("2020-02-02")));
		transactions.add(new TransactionDto("user1",20.0,toDate("2020-03-02")));
		
		transactions.add(new TransactionDto("user2",120.0,toDate("2020-01-02")));
		transactions.add(new TransactionDto("user2",90.0,toDate("2020-01-10")));
		transactions.add(new TransactionDto("user2",20.0,toDate("2020-01-21")));
		HashMap<String,RewardDto> map = service.calculateMonthlyRewards(transactions);
		
		assertEquals(90,map.get("2020-01user1").getRewards());
		assertEquals(40,map.get("2020-02user1").getRewards());
		assertEquals(0,map.get("2020-03user1").getRewards());
		
		assertEquals(130,map.get("2020-01user2").getRewards());
		assertEquals(null,map.get("2020-02user2"));
		assertEquals(null,map.get("2020-03user2"));
	}
	
	private Date toDate(String dateString) {
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException("Error parsing date: " + e.getMessage());
        }
	}
}
