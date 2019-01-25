package com.example.demo.bean;


/*

{"face":[{"position":{"top":8,"left":120,"right":416,"bottom":304},"landmark":{"left_eyebrow_left_corner":{"x":364,"y":42},"left_eyebrow_middle":{"x":377,"y":76},"left_eyebrow_right_corner":{"x":373,"y":115},"right_eyebrow_left_corner":{"x":379,"y":180},"right_eyebrow_middle":{"x":388,"y":225},"right_eyebrow_right_corner":{"x":376,"y":267},"left_eye_left_corner":{"x":330,"y":60},"left_eye_right_corner":{"x":333,"y":112},"right_eye_left_corner":{"x":339,"y":193},"right_eye_right_corner":{"x":342,"y":247},"nose_left":{"x":259,"y":107},"nose_bottom":{"x":247,"y":147},"nose_right":{"x":262,"y":192},"mouth_upper_lip_top":{"x":209,"y":151},"mouth_middle":{"x":189,"y":151},"mouth_lower_lip_bottom":{"x":166,"y":153},"left_eye_center":{"x":335,"y":82},"right_eye_center":{"x":345,"y":215},"nose_top":{"x":273,"y":143},"mouth_left_corner":{"x":182,"y":100},"mouth_right_corner":{"x":187,"y":213}}}],"ret":0}



 */
public class Face {

	private String image_id,request_id,gender,glass,ethnicity,face_token,image_path;
	private int _id,time_used,age,face_rectangle_width,face_rectangle_top,face_rectangle_left,face_rectangle_height;
	private float left_normal_glass_eye_open,left_no_glass_eye_close,left_occlusion,left_no_glass_eye_open
	,left_normal_glass_eye_close,left_dark_glasses,right_normal_glass_eye_open,right_no_glass_eye_close,
			right_occlusion,right_no_glass_eye_open,right_normal_glass_eye_close,right_dark_glasses,
			headpose_yaw_angle,headpose_pitch_angle,headpose_roll_angle,blurness,smile,facequality;

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}


	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public String getFace_token() {
		return face_token;
	}

	public void setFace_token(String face_token) {
		this.face_token = face_token;
	}

	public String getImage_id() {
		return image_id;
	}

	public void setImage_id(String image_id) {
		this.image_id = image_id;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGlass() {
		return glass;
	}

	public void setGlass(String glass) {
		this.glass = glass;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public int getTime_used() {
		return time_used;
	}

	public void setTime_used(int time_used) {
		this.time_used = time_used;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getFace_rectangle_width() {
		return face_rectangle_width;
	}

	public void setFace_rectangle_width(int face_rectangle_width) {
		this.face_rectangle_width = face_rectangle_width;
	}

	public int getFace_rectangle_top() {
		return face_rectangle_top;
	}

	public void setFace_rectangle_top(int face_rectangle_top) {
		this.face_rectangle_top = face_rectangle_top;
	}

	public int getFace_rectangle_left() {
		return face_rectangle_left;
	}

	public void setFace_rectangle_left(int face_rectangle_left) {
		this.face_rectangle_left = face_rectangle_left;
	}

	public int getFace_rectangle_height() {
		return face_rectangle_height;
	}

	public void setFace_rectangle_height(int face_rectangle_height) {
		this.face_rectangle_height = face_rectangle_height;
	}

	public float getLeft_normal_glass_eye_open() {
		return left_normal_glass_eye_open;
	}

	public void setLeft_normal_glass_eye_open(double left_normal_glass_eye_open) {
		this.left_normal_glass_eye_open = (float) left_normal_glass_eye_open;
	}

	public float getLeft_no_glass_eye_close() {
		return left_no_glass_eye_close;
	}

	public void setLeft_no_glass_eye_close(double left_no_glass_eye_close) {
		this.left_no_glass_eye_close = (float) left_no_glass_eye_close;
	}

	public float getLeft_occlusion() {
		return left_occlusion;
	}

	public void setLeft_occlusion(double left_occlusion) {
		this.left_occlusion = (float) left_occlusion;
	}

	public float getLeft_no_glass_eye_open() {
		return left_no_glass_eye_open;
	}

	public void setLeft_no_glass_eye_open(double left_no_glass_eye_open) {
		this.left_no_glass_eye_open = (float) left_no_glass_eye_open;
	}

	public float getLeft_normal_glass_eye_close() {
		return left_normal_glass_eye_close;
	}

	public void setLeft_normal_glass_eye_close(double left_normal_glass_eye_close) {
		this.left_normal_glass_eye_close = (float) left_normal_glass_eye_close;
	}

	public float getLeft_dark_glasses() {
		return left_dark_glasses;
	}

	public void setLeft_dark_glasses(double left_dark_glasses) {
		this.left_dark_glasses = (float) left_dark_glasses;
	}

	public float getRight_normal_glass_eye_open() {
		return right_normal_glass_eye_open;
	}

	public void setRight_normal_glass_eye_open(double right_normal_glass_eye_open) {
		this.right_normal_glass_eye_open = (float) right_normal_glass_eye_open;
	}

	public float getRight_no_glass_eye_close() {
		return right_no_glass_eye_close;
	}

	public void setRight_no_glass_eye_close(double right_no_glass_eye_close) {
		this.right_no_glass_eye_close = (float) right_no_glass_eye_close;
	}

	public float getRight_occlusion() {
		return right_occlusion;
	}

	public void setRight_occlusion(double right_occlusion) {
		this.right_occlusion = (float) right_occlusion;
	}

	public float getRight_no_glass_eye_open() {
		return right_no_glass_eye_open;
	}

	public void setRight_no_glass_eye_open(double right_no_glass_eye_open) {
		this.right_no_glass_eye_open = (float) right_no_glass_eye_open;
	}

	public float getRight_normal_glass_eye_close() {
		return right_normal_glass_eye_close;
	}

	public void setRight_normal_glass_eye_close(double right_normal_glass_eye_close) {
		this.right_normal_glass_eye_close = (float) right_normal_glass_eye_close;
	}

	public float getRight_dark_glasses() {
		return right_dark_glasses;
	}

	public void setRight_dark_glasses(double right_dark_glasses) {
		this.right_dark_glasses = (float) right_dark_glasses;
	}

	public float getHeadpose_yaw_angle() {
		return headpose_yaw_angle;
	}

	public void setHeadpose_yaw_angle(double headpose_yaw_angle) {
		this.headpose_yaw_angle = (float) headpose_yaw_angle;
	}

	public float getHeadpose_pitch_angle() {
		return headpose_pitch_angle;
	}

	public void setHeadpose_pitch_angle(double headpose_pitch_angle) {
		this.headpose_pitch_angle = (float)headpose_pitch_angle;
	}

	public float getHeadpose_roll_angle() {
		return headpose_roll_angle;
	}

	public void setHeadpose_roll_angle(double headpose_roll_angle) {
		this.headpose_roll_angle = (float)headpose_roll_angle;
	}

	public float getBlurness() {
		return blurness;
	}

	public void setBlurness(double blurness) {
		this.blurness = (float) blurness;
	}

	public float getSmile() {
		return smile;
	}

	public void setSmile(double smile) {
		this.smile = (float) smile;
	}

	public float getFacequality() {
		return facequality;
	}

	public void setFacequality(double facequality) {
		this.facequality = (float) facequality;
	}
}
