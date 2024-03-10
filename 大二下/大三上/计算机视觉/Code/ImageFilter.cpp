// ImageFilter.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "opencv2/opencv.hpp"

//using namespace cv;

int main()
{
	//* ex-03
	cv::Mat img_src, img_gray, img_filter, img_binary, img_binary2;
	int i;

	// create window to show image
	char *name[] = { "Original", "Filter", "Binary without filter", "Binary with filter" };
	
	for (i = 0; i < 4; i++)
	{
		cv::namedWindow(name[i], 1);
	}

	// read image from disk
	img_src = cv::imread("02-pillsetc.png", 1);
	if (img_src.empty())
		return 0;

	// convert to gray image
	cv::cvtColor(img_src, img_gray, CV_BGR2GRAY);

	// image filter
	//cv::blur(img_gray, img_filter, cv::Size(3, 3)); //mean filter
	cv::medianBlur(img_gray, img_filter, 3); //median filter
	//cv::GaussianBlur(img_gray, img_filter, cv::Size(3, 3), 1, 1);

	// image threshold
	cv::threshold(img_gray, img_binary, 127, 255, CV_THRESH_BINARY); // half value threshold
	//cv::threshold(img_gray, img_binary, 127, 255, cv::THRESH_OTSU); // auto global threshold value

	// image threshold with filter
	cv::threshold(img_filter, img_binary2, 127, 255, CV_THRESH_BINARY); // auto global threshold value
	//cv::threshold(img_filter, img_binary2, 127, 255, cv::THRESH_OTSU); // auto global threshold value

	// showing results
	cv::imshow(name[0], img_src);
	cv::imshow(name[1], img_filter);
	cv::imshow(name[2], img_binary);
	cv::imshow(name[3], img_binary2);

	cv::waitKey(0);

	cv::destroyAllWindows();
	return 0;
}