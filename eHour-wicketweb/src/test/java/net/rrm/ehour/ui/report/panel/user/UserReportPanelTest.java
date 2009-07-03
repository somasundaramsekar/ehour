/*
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 * 
 * eHour is sponsored by TE-CON  - http://www.te-con.nl/
 */

package net.rrm.ehour.ui.report.panel.user;


import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import net.rrm.ehour.report.criteria.ReportCriteria;
import net.rrm.ehour.report.criteria.ReportCriteriaUpdateType;
import net.rrm.ehour.ui.common.util.CommonWebUtil;
import net.rrm.ehour.ui.report.aggregate.CustomerAggregateReport;
import net.rrm.ehour.ui.report.chart.aggregate.AggregateChartImage;
import net.rrm.ehour.ui.report.page.BaseTestReport;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.TestPanelSource;
import org.junit.Before;
import org.junit.Test;

/**
 * Created on Jun 16, 2009, 11:41:44 AM
 * @author Thies Edeling (thies@te-con.nl) 
 *
 */
public class UserReportPanelTest extends BaseTestReport
{
	@Before
	public void setUp() throws Exception
	{
		super.setUp();

	}
	
	@Test
	public void shouldRenderWithGraphs()
	{
		Panel panel = start();
		
		List<AggregateChartImage> img = CommonWebUtil.findComponent(panel, AggregateChartImage.class);
		assertNotNull(img);
	}
	
	@SuppressWarnings("serial")
	private Panel start()
	{
		expect(reportCriteriaService.syncUserReportCriteria(isA(ReportCriteria.class), eq(ReportCriteriaUpdateType.UPDATE_ALL)))
		.andReturn(reportCriteria);	

		expect(aggregateReportService.getAggregateReportData(reportCriteria))
				.andReturn(data);
		
		replay(reportCriteriaService);
		replay(aggregateReportService);

		final CustomerAggregateReport	customerAggregateReport = new CustomerAggregateReport(reportCriteria);
		
		Panel startPanel = getTester().startPanel(new TestPanelSource()
		{
			public Panel getTestPanel(String panelId)
			{
				return new UserReportPanel(panelId, customerAggregateReport, UserReportPanel.Option.INCLUDE_LINKS);
			}
		});
		
		return startPanel;
	}

}
