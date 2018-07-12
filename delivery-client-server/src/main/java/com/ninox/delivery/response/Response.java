package com.ninox.delivery.response;

import java.util.List;

public class Response<T> {

	private T data;
	private List<String> mensagens;
	private Boolean valido;

	public Response(T data) {
		this.data = data;
	}

	public Response(List<String> erros) {
		this.mensagens = erros;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<String> getErros() {
		return mensagens;
	}

	public void setErros(List<String> erros) {
		this.mensagens = erros;
	}

	public Boolean getValido() {
		return valido;
	}

	public void setValido(Boolean valido) {
		this.valido = valido;
	}
}
