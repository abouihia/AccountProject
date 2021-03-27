package com.example.account.controller;

import com.example.account.model.BankAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BankAccountApplicationTests {

	@Autowired
	private BankAccount bankAccount;

	@Autowired
	private MockMvc mockMvc;

	public static  final String amountDeposit= "100";


	@Test
	public void shouldReturnResponseWithStatusOkWhenCallGetInfoBalance() throws Exception {

		final var uriInfoBalance = "/infoBalance";
		final var mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uriInfoBalance)
				.accept(APPLICATION_JSON_VALUE)).andReturn();

		final var response = mvcResult.getResponse();
		assertThat(response.getStatus()).isEqualTo(OK.value());
		assertThat(response.getContentType()).isEqualTo(APPLICATION_JSON_VALUE);
		assertThat(response.getContentAsString()).contains("Your Balance Account is :");

	}


	@Test
	public void shouldReturnResponseWithStatusOkWhenCallGetInfoInfoOperationAccount() throws Exception {

		final var uriInfoOperationAccount = "/infoOperationsAccount";
		final var mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uriInfoOperationAccount)
				.accept(APPLICATION_JSON_VALUE)).andReturn();

		final var response = mvcResult.getResponse();
		assertThat(response.getStatus()).isEqualTo(OK.value());
		assertThat(response.getContentType()).isEqualTo(APPLICATION_JSON_VALUE);
		assertThat(response.getContentAsString())
				.contains("This the History of all your Operation  in your Account :");

	}

	@Test
	public void shouldReturnResponseWithStatusOkWhenCallPutToMakeDeposit() throws Exception {

		final var uriDeposit = "/depositInAccount";
		final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uriDeposit)
				.contentType(APPLICATION_JSON_VALUE).content(amountDeposit)).andReturn();

		final var response = mvcResult.getResponse();
		assertThat(response.getStatus()).isEqualTo(OK.value());
		assertThat(response.getContentType()).isEqualTo(APPLICATION_JSON_VALUE);
		assertThat(response.getContentAsString())
				.contains("Your Balance Account is :"+amountDeposit);
	}


	@Test
	public void shouldReturnResponseWithdrawOkWithStatusOkWhenCallPutToMakeWithdraw() throws Exception {

        //given
		 var uriDeposit = "/depositInAccount";
		 MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uriDeposit)
				.contentType(APPLICATION_JSON_VALUE).content(amountDeposit)).andReturn();

		 //when
	     uriDeposit = "/withdrawInAccount";
		 mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uriDeposit)
				.contentType(APPLICATION_JSON_VALUE).content(amountDeposit)).andReturn();

		 //then
		final var response = mvcResult.getResponse();
		assertThat(response.getStatus()).isEqualTo(OK.value());
		assertThat(response.getContentType()).isEqualTo("text/plain;charset=UTF-8");
		assertThat(response.getContentAsString())
				.contains("withdraw :"+amountDeposit);
	}

	@Test
	public void shouldReturnResponseWithdrawKoWithStatusOkWhenCallPutToMakeWithdraw() throws Exception {

		//given
		var uri = "/depositInAccount";
		final var amountDeposit= "50";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
				.contentType(APPLICATION_JSON_VALUE).content(amountDeposit)).andReturn();

		//when
		uri = "/withdrawInAccount";
		final var amountWithdraw= "100";
		mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
				.contentType(APPLICATION_JSON_VALUE).content(amountWithdraw)).andReturn();

		//then
		final var response = mvcResult.getResponse();
		assertThat(response.getStatus()).isEqualTo(OK.value());
		assertThat(response.getContentType()).isEqualTo("text/plain;charset=UTF-8");
		assertThat(response.getContentAsString())
				.contains("insufficient balance :"+amountDeposit);
	}
}
