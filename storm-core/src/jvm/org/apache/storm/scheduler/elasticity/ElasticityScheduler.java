package org.apache.storm.scheduler.elasticity;

import org.apache.storm.scheduler.*;
import org.apache.storm.scheduler.elasticity.MsgServer.MsgServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ElasticityScheduler implements IScheduler {
	private static final Logger LOG = LoggerFactory
			.getLogger(ElasticityScheduler.class);
	static int flag = 0; // Todo : remove this later. this is to make sure we do scale out only once
	@SuppressWarnings("rawtypes")
	private Map _conf;

	@Override
	public void prepare(@SuppressWarnings("rawtypes") Map conf) {
		_conf = conf;
	}

	@Override
	public void schedule(Topologies topologies, Cluster cluster) {
		LOG.info("\n\n\nRerunning ElasticityScheduler...\n");
 		MsgServer msgServer = MsgServer.start(GlobalConstants.MsgServerPort);

		MsgServer.Signal signal = msgServer.getMessage();
		if(signal == MsgServer.Signal.ScaleOut) {
			LOG.info("Scale out Message Sent\n\n");
		} else if (signal == MsgServer.Signal.ScaleIn) {
			LOG.info("Scale In Message Sent\n\n");
		} else {
			LOG.info("No Signal Sent\n\n");
		}

		LOG.info("calling default scheduler from Elasticity\n");
		new org.apache.storm.scheduler.DefaultScheduler().schedule(
 			topologies, cluster);
	}
}

