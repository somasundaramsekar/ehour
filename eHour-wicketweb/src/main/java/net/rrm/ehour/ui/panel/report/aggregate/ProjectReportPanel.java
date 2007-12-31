/**
 * Created on Sep 27, 2007
 * Created by Thies Edeling
 * Created by Thies Edeling
 * Copyright (C) 2007 TE-CON, All Rights Reserved.
 *
 * This Software is copyright TE-CON 2007. This Software is not open source by definition. The source of the Software is available for educational purposes.
 * TE-CON holds all the ownership rights on the Software.
 * TE-CON freely grants the right to use the Software. Any reproduction or modification of this Software, whether for commercial use or open source,
 * is subject to obtaining the prior express authorization of TE-CON.
 * 
 * thies@te-con.nl
 * TE-CON
 * Legmeerstraat 4-2h, 1058ND, AMSTERDAM, The Netherlands
 *
 */

package net.rrm.ehour.ui.panel.report.aggregate;

import net.rrm.ehour.report.reports.ReportData;
import net.rrm.ehour.ui.panel.report.ReportType;
import net.rrm.ehour.ui.report.TreeReport;
import net.rrm.ehour.ui.reportchart.aggregate.ProjectHoursAggregateChartImage;
import net.rrm.ehour.ui.reportchart.aggregate.ProjectTurnoverAggregateChartImage;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.Model;

/**
 * TODO 
 **/

public class ProjectReportPanel extends AggregateReportPanel
{
	private static final long serialVersionUID = 2594554714722639450L;

	public ProjectReportPanel(String id, TreeReport reportData, ReportData data)
	{
		super(id, reportData, data, ReportType.AGGREGATE_PROJECT, "projectReportExcel");
	}

	@Override
	protected void addCharts(ReportData data, WebMarkupContainer parent)
	{
		Model dataModel = new Model(data);

		// hours per customer
		ProjectHoursAggregateChartImage hoursChart = new ProjectHoursAggregateChartImage("hoursChart", dataModel, chartWidth, chartHeight);
		parent.add(hoursChart);

		ProjectTurnoverAggregateChartImage turnoverChart = new ProjectTurnoverAggregateChartImage("turnoverChart", dataModel, chartWidth, chartHeight);
		parent.add(turnoverChart);	}

}