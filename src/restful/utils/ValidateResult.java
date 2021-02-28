package restful.utils;

import restful.bean.Result;

public class ValidateResult {
	private boolean valid;
	private String message;

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ValidateResult(boolean valid, String message) {
		super();
		this.valid = valid;
		this.message = message;
	}

	public Result toResult() {
		int code = valid ? 1 : -1;
		String description = message;
		return new Result(code, description, null, "");
	}

	public void printfValidateMessage() {
		System.out.println("result:" + valid + ",message:" + message);
	}

	@Override
	public String toString() {
		return "ValidateResult [valid=" + valid + ", message=" + message  + "]";
	}

	
}
