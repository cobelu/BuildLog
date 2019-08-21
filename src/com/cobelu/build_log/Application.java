package com.cobelu.build_log;

import com.cobelu.build_log.model.Model;
import com.cobelu.build_log.view.ApplicationFrame;

public class Application {

	public static void main(String[] args) {

		Model model = new Model();
		ApplicationFrame appFrame = new ApplicationFrame(model);
		appFrame.setVisible(true);
		
	}

}
