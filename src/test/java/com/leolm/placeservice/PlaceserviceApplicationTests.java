package com.leolm.placeservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leolm.placeservice.dtos.PlaceRequestDto;
import com.leolm.placeservice.dtos.PlaceResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Slf4j
class PlaceserviceApplicationTests {
	@Autowired
	ObjectMapper objectMapper ;

	String url = "http://localhost:8080/places";

	@Test
	public void testCreatePlaceSucess() throws URISyntaxException, IOException, InterruptedException {
		String name = "Valid Name";
		String state = "Valid State";
		String slug = "valid-name";


		PlaceRequestDto placeRequestDto = new PlaceRequestDto(name,state);
		String requestBody =  objectMapper.writeValueAsString(placeRequestDto);

		HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI(url))
				.headers("Content-Type", "application/json;charset=UTF-8") //todo adicionar json aqui e restringir a api a jsons
				.POST(HttpRequest.BodyPublishers.ofString(
						requestBody))
				.build();

		HttpClient httpClient = HttpClient.newHttpClient();
		var response =
				httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		log.info("Resposta API {}",response.body());
		PlaceResponseDto placeResponseDto = objectMapper.readValue(response.body(),PlaceResponseDto.class);

		Assertions.assertEquals(name,placeResponseDto.name());
		Assertions.assertEquals(state,placeResponseDto.state());
		Assertions.assertEquals(slug,placeResponseDto.slug());
		Assertions.assertNotNull(placeResponseDto.createdAt());
		Assertions.assertNotNull(placeResponseDto.updatedAt());
		Assertions.assertEquals(HttpStatus.CREATED.value(),response.statusCode());
	}



	@Test
	public void testInvalidContentType() throws URISyntaxException, IOException, InterruptedException {
		String name = "Valid Name";
		String state = "Valid State";
		String slug = "valid-name";


		PlaceRequestDto placeRequestDto = new PlaceRequestDto(name,state);
		String requestBody =  objectMapper.writeValueAsString(placeRequestDto);

		HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI(url))
				.headers("Content-Type", "invalidheader;charset=UTF-8")
				.POST(HttpRequest.BodyPublishers.ofString(
						requestBody))
				.build();

		HttpClient httpClient = HttpClient.newHttpClient();
		var response =
				httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		log.info("Resposta API {}",response.body());
		Assertions.assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),response.statusCode());
	}

	@Test
	public void testMissingRequestParameter() throws URISyntaxException, IOException, InterruptedException {
		String name = "Valid Name";
		String state = "Valid State";
		String slug = "valid-name";


		PlaceRequestDto placeRequestDto = new PlaceRequestDto(null,state);
		String requestBody =  objectMapper.writeValueAsString(placeRequestDto);

		HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI(url))
				.headers("Content-Type", "application/json;charset=UTF-8")
				.POST(HttpRequest.BodyPublishers.ofString(
						requestBody))
				.build();

		HttpClient httpClient = HttpClient.newHttpClient();
		var response =
				httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		log.info("Resposta API {}",response.body());
		Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(),response.statusCode());
	}


}
