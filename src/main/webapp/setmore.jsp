<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	
	<title>Printable Calendar</title>
	
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
  	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
  	<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css" rel="stylesheet">
</head>
<body>
	<div class="layer">
		<div class="header">
			<div>
				<ul>
					<li>
						<a>
							<img src="img/logo/logo.png" srcset="img/logo/logo@2x.png 2x,img/logo/logo@3x.png 3x" class="logo">
						</a>
					</li>
					<li>
						<a>
							<span>Integrations</span>
						</a>
					</li>
					<li>
						<a>
							<span>Premium</span>
						</a>
					</li>
					<li>
						<a>
							<span>Partners</span>
						</a>
					</li>
					<li>
						<a>
							<span>Support</span>
						</a>
					</li>
				</ul>
			</div>
			<div class="header-action">
				<button class="btn white-btn login">
					LOGIN
				</button>
				<button class="btn rose-btn start-now">
					START NOW
				</button>
			</div>
		</div>
		<div class="body">
			<div class="section left gridify calendar">
				<div class="section-header">
					<h3>Download your Printable Calendar</h3>
					<ul>
						<li class="active">MONTHLY</li>
						<li>YEARLY</li>
						<li>WEEKLY</li>
						<li>DAILY</li>
					</ul>
				</div>
			</div>
			<div class="section left gridify calendar">
				<div class="section-body">
					<div class="content">
						<div class="section-info">
							Create your Calendar
						</div>
						<form action="pdf/calendar.pdf" method="post" modelAttribute="details">
						<div class="input-text input-custom-dropdown">
							<span style="float:left;">From<span style="margin-left: 25px">:</span></span>
							
							<div href="javascript:void(0)" style="text-decoration: none;float:left;margin-left:30px; position: relative;">
								<!-- <label>Month</label>
								<img style="width: 14px; height: 14px; top: 0px;margin-top: 5px;margin-right: 4px;" src="img/sections/rectangle.png"> -->    
								<select name="fromMonth" class="selectpicker">
								  <option value="January">January</option>
								  <option value="February">February</option>
								  <option value="March">March</option>
								  <option value="April">April</option>
								  <option value="May">May</option>
								  <option value="June">June</option>
								  <option value="July">July</option>
								  <option value="August">August</option>
								  <option value="September">September</option>
								  <option value="October">October</option>
								  <option value="November">November</option>
								  <option value="December">December</option>
								</select>
							</div>
							<div href="javascript:void(0)" style="text-decoration: none;float:left;margin-left:60px; position: relative;">
								<label>Year</label>
								<input type="text" name="startYear" placeholder="Year"/>
								<span class="divider"></span>
							</div>
							<div class="clearfix"></div>
						</div>
					
						<div class="input-text input-custom-dropdown">
							<span style="float:left;">To<span style="margin-left: 45px">:</span></span>
							
							<a href="javascript:void(0)" style="text-decoration: none;float:left;margin-left:30px; position: relative;">
								<!-- <label>Month</label>
								<img style="width: 14px; height: 14px; top: 0px;margin-top: 5px;margin-right: 4px;" src="img/sections/rectangle.png"> -->    
								<select name="ToMonth" class="selectpicker">
								  <option value="Jan">January</option>
								  <option value="Feb">February</option>
								  <option value="March">March</option>
								  <option value="April">April</option>
								  <option value="May">May</option>
								  <option value="June">June</option>
								  <option value="July">July</option>
								  <option value="August">August</option>
								  <option value="September">September</option>
								  <option value="October">October</option>
								  <option value="November">November</option>
								  <option value="December">December</option>
								  
								</select>
							</a>
							
							<a href="javascript:void(0)" style="text-decoration: none;float:left;margin-left:60px; position: relative;">
								<label>Year</label>
								<input type="text" name="endYear"  placeholder="Year"/>
								<span class="divider"></span>
							</a>
							<div class="clearfix"></div>
						</div>
							<div class="form-group">
					        	<div class="col-xs-5 col-xs-offset-3">
					            	<button type="submit" class="btn btn-success">Download</button>
					        	</div>
					    	</div>
					</form>

					</div>
					<div class="image">
						<img src="img/sections/calendar.png" srcset="img/sections/calendar@2x.png 2x, img/sections/calendar@3x.png 3x">
					</div>
				</div>
			</div>
			<div class="section left gridify printer">
				<div class="section-body">
					<div class="content">
						<h3>Why you don’t need to print this calendar</h3>
						<p class="section-info"> Don’t print this calendar if you can help it. </p>
						<span class="divider"></span>
						<p> Regularly printing calendars and schedules doesn’t make sense. 
							It’s easier to create, edit, and share calendars digitally rather than constantly keep a paper version up to date.
						</p>
						<p> In addition, office printers churning out paper can be a carbon intensive process in your organisation. And it’s not the just paper. 
							Your printer itself has an embodied carbon footprint, and by avoiding unnecessary printing you can extend its useful life.
						</p>
						<p> To help you avoid printing calendar templates unnecessarily, we’ve created a flexible PDF template with a date range that you can select yourself. 
							Any year and month is available past, present, or future. 
						</p>
					</div>
					<div class="image">
						<img src="img/sections/printer.png" srcset="img/sections/printer@2x.png 2x, img/sections/printer@3x.png 3x">
					</div>
				</div>
			</div>
			<div class="section right gridify isometric-stack">
				<div class="section-body">
					<div class="content">
						<h3>How to use your calendar template</h3>
						<p class="section-info"> The calender is completly editable and shareable</p>
						<span class="divider"></span>
						<p> Once you’ve downloaded your PDF template, you’ll find that each box within the calendar is editable. 
							Select the appropriate box and you’ll be able to type directly onto your calendar.
						</p>
						<p> You can then share the calendar with your team, who will be able to update the calendar themselves. 
							It’s a better option than trading a paper calendar back and forth every time.
						</p>
						<p> Our calendar template has been designed for you to use as a reusable, editable, and environmentally-friendly calendar scheduler. 
							<br>
							We hope you like it.
						</p>
						<p> Feel free to download as many versions as you like, as often as you like.</p>
					</div>
					<div class="image">
						<img src="img/sections/isometric-stack.png" srcset="img/sections/isometric-stack@2x.png 2x, img/sections/isometric-stack@3x.png 3x">
					</div>
				</div>
			</div>
			<div class="section left gridify digi-schedule">
				<div class="section-body">
					<div class="content">
						<h3>Digital scheduling on all your devices</h3>
						<p class="section-info"> A PDF calendar template isn’t the only way to avoid printing out dozens of month-to-month calendars every year.</p>
						<span class="divider"></span>
						<p> Instead of a printable calendar, consider the many other options for scheduling your time that make it easier for you to organise, 
							keep up to date, and share with your team.
						</p>
						<p> Google Calendar, Office 365 Outlook, and Apple iCal can all help you organise your time and schedule your appointments 
							without having to resort to printables. But sometimes these regular calendar applications don’t quite meet your specific requirements. 
							<br>
							Setmore is our solution to your scheduling problem.
						</p>
						<p> It’s designed to help you when you need more than just a basic calendar.</p>
						<p> 	<em>•</em><em>Self-service portal</em><br>
								<em>•</em><em>Take appointments 24/7</em><br>
								<em>•</em><em>Embed on website</em>
						</p>
						<p> … then you might find that Setmore is more effective for your organisation than a downloadable template or basic calendar app. 
						</p>
					</div>
					<div class="image">
						<img src="img/sections/device-mock-ups.png" srcset="img/sections/device-mock-ups@2x.png 2x, img/sections/device-mock-ups@3x.png 3x">
					</div>
				</div>
			</div>
			<div class="section try-us left">
				<div class="section-body gridify">
					<div class="content">
						<h3>We’d love for you to try us. </h3>
						<span>Want to see if Setmore might help you ?</span>
						<button class="btn white-btn get-started">
							GET STARTED
						</button>
					</div>
					<div class="image">
						<img src="img/sections/angled-devices.png" srcset="img/sections/angled-devices@2x.png 2x, img/sections/angled-devices@3x.png 3x">
					</div>
				</div>
			</div>
		</div>
	</div>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>
</body>
</html>