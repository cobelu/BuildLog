package com.cobelu.build_log.controller;

import javafx.scene.input.MouseEvent;

public class ControllerHelper {

	public static boolean isDoubleClick(MouseEvent event) {
		boolean isClick = event.isPrimaryButtonDown();
		boolean isDouble = event.getClickCount() == 2;
		return isClick && isDouble;
	}

}
