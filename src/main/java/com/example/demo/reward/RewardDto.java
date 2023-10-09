package com.example.demo.reward;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RewardDto {	
	private String customerId;
	private int rewards;
	private String period;
}
