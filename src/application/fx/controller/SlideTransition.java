package application.fx.controller;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

//封装几个动画
public class SlideTransition {
	public void inTransition(Node fromNode, Node toNode, Duration duration) {

		this. inTransition(fromNode, toNode, duration, null);

	}

	public void inTransition(Node fromNode, Node toNode, Duration duration, EventHandler<ActionEvent> completion) {

		// TranslateTransition translateTransition=new
		// TranslateTransition(duration, fromNode);
		//
		// translateTransition.setFromX(50);
		//
		// translateTransition.setToX(350);

		slideIn((Pane)fromNode, (Pane)toNode, duration, completion);
		// fade(fromNode, toNode, duration, completion);
	}
	public void outTransition(Node fromNode, Node toNode, Duration duration, EventHandler<ActionEvent> completion) {

		slideOut((Pane)fromNode, (Pane)toNode, duration, completion);
	}

	private void slideIn(Pane fromNode, Pane toNode, Duration duration, EventHandler<ActionEvent> completion) {

		TranslateTransition fromTransition = new TranslateTransition(duration, fromNode);

		TranslateTransition toTransition = new TranslateTransition(duration, toNode);

		double width = fromNode.getWidth();
		double fromViewX = fromNode.getLayoutX();

		double fromViewToX = fromViewX - width;

		System.out.println("x = " + fromViewX + " " + width + " " + fromViewToX);
		
		double toViewX = fromViewX + width;

		double toViewToX = fromViewX;
		System.out.println("x = " + toViewX + " " + width + " " + toViewToX);

		// 设置一下当前的坐标
//		toNode.setLayoutX(toViewX);

		fromTransition.setFromX(fromViewX);

		fromTransition.setToX(fromViewToX);

		//
		toTransition.setFromX(toViewX);

		toTransition.setToX(toViewToX);

		ParallelTransition parallelTransition = new ParallelTransition(fromTransition,toTransition);

		parallelTransition.play();
		parallelTransition.setOnFinished(e -> System.out.println(fromNode.getLayoutX()));

	}
	
	private void slideOut(Pane fromNode, Pane toNode, Duration duration, EventHandler<ActionEvent> completion) {

		TranslateTransition fromTransition = new TranslateTransition(duration, fromNode);

		TranslateTransition toTransition = new TranslateTransition(duration, toNode);

		double width = fromNode.getWidth();
		double fromViewX = 0;

		double fromViewToX = width;

		System.out.println("x = " + fromViewX + " " + width + " " + fromViewToX);
		
		double toViewX = -width;

		double toViewToX = 0;
		System.out.println("x = " + toViewX + " " + width + " " + toViewToX);

		// 设置一下当前的坐标
//		toNode.setLayoutX(toViewX);

		fromTransition.setFromX(fromViewX);

		fromTransition.setToX(fromViewToX);

		//
		toTransition.setFromX(toViewX);

		toTransition.setToX(toViewToX);

		ParallelTransition parallelTransition = new ParallelTransition(fromTransition,toTransition);

		parallelTransition.play();
		parallelTransition.setOnFinished(e -> System.out.println(fromNode.getLayoutX()));

	}


	private void fade(Node fromNode, Node toNode, Duration duration, EventHandler<ActionEvent> completion) {
		FadeTransition fadeTransition = new FadeTransition(duration, fromNode);

		fadeTransition.setFromValue(1.0f);

		fadeTransition.setToValue(0);

		FadeTransition toFadeTransition = new FadeTransition(duration, toNode);

		toFadeTransition.setFromValue(0);

		toFadeTransition.setToValue(1);

		// 顺序执行
		SequentialTransition sequentialTransition = new SequentialTransition(fadeTransition, toFadeTransition);
		sequentialTransition.play();

		sequentialTransition.setOnFinished(completion);
	}
}
