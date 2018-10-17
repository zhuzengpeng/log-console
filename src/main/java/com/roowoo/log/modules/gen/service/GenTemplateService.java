package com.roowoo.log.modules.gen.service;

import com.roowoo.log.common.persistence.Page;
import com.roowoo.log.common.service.BaseService;
import com.roowoo.log.common.utils.StringUtils;
import com.roowoo.log.modules.gen.dao.GenTemplateDao;
import com.roowoo.log.modules.gen.entity.GenTemplate;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 代码模板Service
 * @author ThinkGem
 * @version 2013-10-15
 */
@Service
public class GenTemplateService extends BaseService {

	@Autowired
	private GenTemplateDao genTemplateDao;
	
	public GenTemplate get(String id) {
		return genTemplateDao.get(id);
	}
	
	public Page<GenTemplate> find(Page<GenTemplate> page, GenTemplate genTemplate) {
		genTemplate.setPage(page);
		page.setList(genTemplateDao.findList(genTemplate));
		return page;
	}
	
	public void save(GenTemplate genTemplate) {
		if (genTemplate.getContent()!=null){
			genTemplate.setContent(StringEscapeUtils.unescapeHtml4(genTemplate.getContent()));
		}
		if (StringUtils.isBlank(genTemplate.getId())){
			genTemplate.preInsert();
			genTemplateDao.insert(genTemplate);
		}else{
			genTemplate.preUpdate();
			genTemplateDao.update(genTemplate);
		}
	}
	
	public void delete(GenTemplate genTemplate) {
		genTemplateDao.delete(genTemplate);
	}
	
}
