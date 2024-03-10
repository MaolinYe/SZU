// ImageStatistics.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "opencv2/opencv.hpp"
//using namespace cv;

int main()
{
	/* ex-02-1
	cv::Mat img_gray, img_rgb, img_clip, img_roi, img_opr;
	int i, x, y;
	uchar pixel_gray;
	cv::Vec3b pixel_rgb;	

	// create window to show image
	char *name[] = { "Gray", "RGB", "Clip", "ROI", "Operation"};
	for (i = 0; i < 5; i++)
	{
		cv::namedWindow(name[i], 1);
	}

	// read image from disk
	img_gray = cv::imread("circlesBrightDark.png", 0);
	img_rgb = cv::imread("coloredChips.png", 1);
	if (img_gray.empty() || img_rgb.empty())
		return 0;
	else
	{
		printf("Gray image size:(height-%d, width-%d), nchannels:%d\n", img_gray.rows, img_gray.cols, img_gray.channels());
		printf("Color image size:(height-%d, width-%d), nchannels:%d\n", img_rgb.rows, img_rgb.cols, img_rgb.channels());
	}

	// access the pixel of image
	x = y = 10;
	pixel_gray = img_gray.at<uchar>(y, x);
	pixel_rgb = img_rgb.at<cv::Vec3b>(y, x); //[0]:blue, [1]:green, [2]:red
	printf("Gray pixel value at (%d, %d) is %d\n", y, x, pixel_gray);
	printf("RGB pixel values at (%d, %d) is (%d, %d, %d)\n", y, x, pixel_rgb[2], pixel_rgb[1], pixel_rgb[0]);

	// clip image
	cv::Rect roi_rect = cv::Rect(105, 18, 50, 50);
	img_clip = img_rgb(roi_rect);

	// image roi
	img_roi = img_rgb.clone();
	img_roi(roi_rect).setTo(cv::Scalar(255, 255, 255));

	// Statistics infos
	cv::Scalar mean, std;
	cv::Point  min_pt, max_pt;
	double min_val = 0, max_val = 0;

	cv::meanStdDev(img_gray, mean, std);
	printf("\nThe mean and std values of gray image are %.2f,%.2f\n", mean.val[0], std.val[0]);

	cv::minMaxLoc(img_gray, &min_val, &max_val, &min_pt, &max_pt);
	printf("Minimum value %.2f at (%d, %d)\n", min_val, min_pt.y, min_pt.x);
	printf("Maximum value %.2f at (%d, %d)\n", max_val, max_pt.y, max_pt.x);

	img_opr = 255 - img_gray;

	// show image
	cv::imshow(name[0], img_gray);
	cv::imshow(name[1], img_rgb);
	cv::imshow(name[2], img_clip);
	cv::imshow(name[3], img_roi);
	cv::imshow(name[4], img_opr);

	cv::imwrite("mypic.jpg", img_opr);
	cv::waitKey(0);

	cv::destroyAllWindows();
	return 0;
	*/
	
	//* ex-02
	cv::Mat img_rgb, img_resize, img_flip;
	int i;

	// create window to show image
	char *name[] = { "Original", "Resize", "Flip", "Rotate", "Unconstrained Rotate" };
	for (i = 0; i < 5; i++)
	{
		cv::namedWindow(name[i], 1);
	}

	// read image from disk
	img_rgb = cv::imread("coloredChips.png", 1);
	if (img_rgb.empty())
		return 0;

	// resize image
	cv::resize(img_rgb, img_resize, cv::Size(256, 256), 0, 0, 1);

	// flip image
	cv::flip(img_rgb, img_flip, -1);

	// rotate image
	int rotate_degree = 45;
	cv::Size src_sz = img_rgb.size();
	cv::Point2f center(cvRound(img_rgb.cols / 2.0), cvRound(img_rgb.rows / 2.0));
	cv::Rect2f bbox;
	cv::Mat img_rotate, img_urotate, rot_mat;
	
	rotate_degree = 45;
	rot_mat = cv::getRotationMatrix2D(center, rotate_degree, 1.0);
	cv::warpAffine(img_rgb, img_rotate, rot_mat, src_sz);

	// unconstrained rotate image
	bbox = cv::RotatedRect(cv::Point2f(), img_rgb.size(), rotate_degree).boundingRect();
	// adjust transformation matrix--translation transformation
	rot_mat.at<double>(0, 2) += bbox.width / 2.0 - img_rgb.cols / 2.0;
	rot_mat.at<double>(1, 2) += bbox.height / 2.0 - img_rgb.rows / 2.0;
	cv::warpAffine(img_rgb, img_urotate, rot_mat, bbox.size());

	cv::imshow(name[0], img_rgb);
	cv::imshow(name[1], img_resize);
	cv::imshow(name[2], img_flip);
	cv::imshow(name[3], img_rotate);
	cv::imshow(name[4], img_urotate);

	cv::waitKey(0);

	cv::destroyAllWindows();
	return 0;
	//*/
}

