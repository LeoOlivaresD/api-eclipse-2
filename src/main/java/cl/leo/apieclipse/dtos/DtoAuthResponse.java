package cl.leo.apieclipse.dtos;

import lombok.Data;

@Data
public class DtoAuthResponse {
	private String accessToken;
	private String tokentype = "Bearer ";

	public DtoAuthResponse(String accessToken) {
		super();
		this.accessToken = accessToken;
	}

}
