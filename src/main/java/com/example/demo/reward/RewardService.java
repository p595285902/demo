package com.example.demo.reward;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RewardService{
	
	static final int REWARD_LIMIT_S=50;
	static final int REWARD_LIMIT_L=100;
	static final String PERIOD_FORMAT = "yyyy-MM";
	
	public HashMap<String,RewardDto> calculateMonthlyRewards(List<TransactionDto> transactions) {
		HashMap<String,RewardDto> map = new HashMap<>();
		for(TransactionDto t : transactions) {
			int reward = calcReward(t);
	        SimpleDateFormat sdf = new SimpleDateFormat(PERIOD_FORMAT);
	        String period = sdf.format(t.getTime());
	        String key = period+t.getCustomerId();
	        RewardDto val = map.get(key);
	        if(val != null) {
	        	val.setRewards(val.getRewards()+reward);
	        }else {
	        	RewardDto rewardDto = new RewardDto(t.getCustomerId(),reward,period);
				map.put(key, rewardDto );
	        }
		}
		return map;
	}
	
	private int calcReward(TransactionDto trans) {
		int ret = 0;
		double spent = trans.getSpent();
		if(spent>REWARD_LIMIT_L)
			ret+=(trans.getSpent()-REWARD_LIMIT_L) * 2;
		if(spent>REWARD_LIMIT_S)
			ret+=Math.min(REWARD_LIMIT_S, spent-50);
		return ret;
	}
}

