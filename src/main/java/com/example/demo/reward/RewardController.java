package com.example.demo.reward;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reward")
public class RewardController {

	@Autowired
	RewardService rewardService;

	@PostMapping(value = "/calc")
	public Collection<RewardDto> calculateMonthlyReward(@RequestBody List<TransactionDto> transactions) {
		return rewardService.calculateMonthlyRewards(transactions).values();
	}
}
